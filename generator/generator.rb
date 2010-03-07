require 'rubygems'
require 'xmlsimple'
require 'facets/dictionary'

BASE_PACKAGE = 'de.eeriedaily.namqp.v08.methods'

### templates and helper methods

def escape_doc d 
  d.find { |e| e.is_a?(String) }.split("\n").map { |e| e.strip }.reject { |e| e.length.zero? }.map do |e|
    " * #{e.squeeze(' ')}"
  end.join("\n") rescue nil 
end

def escape_method_name n
  [ n.split[0], n.split[1..-1].map { |e| e.capitalize } ].join
end

def method_missing m
#  p m.inspect
  @b.eval(m.to_s)
end

def set b 
  @b = b
end

def types2assignments f
  f.map { |k,v| "this.#{k} = #{k};" }.join("\n\t\t")
end

def types2initializer f

  bits = -1;

  "this(#{f.map do |k,v|
    
    type = v['type']
  
    if (type == 'Bit')
      "new #{type}(channelBuffer, #{bits += 1})"
    else
      bits = -1
      "new #{type}(channelBuffer)" 
    end
  
  end.join(', ')});"
end

def types2fields f
  f.map { |k,v| "private #{v['type']} #{k};" }.join("\n\t")
end

def types2getters f
  f.map do |k,v|
    <<-JAVA
    public #{v['type']} get#{k[0...1].upcase + k[1..-1]}() {
        return this.#{k};
    }    
JAVA
  end.join("\n")
end

def types2parameters f
  f.map { |k,v| "#{v['type']} #{k}" }.join(', ')
end

def types2references f

  refs = []
  bit_stack = []

  clear_stack = proc do
    unless bit_stack.empty?
      refs << "new BitField(#{bit_stack.join(', ')})"
      bit_stack.clear
    end
  end

  f.each do |k,v|

    if (v['type'] == 'Bit')
      bit_stack << k
    else
      clear_stack.call
      refs << k
    end
    
  end
  
  clear_stack.call
  
  refs.join(', ')
  
end

klass = proc do
  <<-JAVA
package #{BASE_PACKAGE}.#{class_name};

import de.eeriedaily.namqp.v08.methods.AbstractMethod;
import de.eeriedaily.namqp.v08.types.*;

import javax.annotation.Generated;

/**
#{doc}
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public abstract class #{class_classname} extends AbstractMethod {

    public static final UnsignedShort CLASS_ID = new UnsignedShort(#{class_index});
    protected static final String CLASS_NAME = "#{class_name}";

    public final UnsignedShort getMethodClassId() {
        return CLASS_ID;
    }

    @Override
    protected final String getMethodClassName() {
        return CLASS_NAME;
    }
    
}    
JAVA
  
end

method = proc do
  <<-JAVA
package #{BASE_PACKAGE}.#{class_name};

import de.eeriedaily.namqp.v08.Transmittable;
import de.eeriedaily.namqp.v08.types.*;
import org.jboss.netty.buffer.ChannelBuffer;  

import javax.annotation.Generated;

/**
#{doc}
 * 
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class #{method_classname} extends #{class_classname} {

    public static final UnsignedShort METHOD_ID = new UnsignedShort(#{method_index});
    protected static final String METHOD_NAME = "#{method_name}";

    #{types2fields(types)}

    public #{method_classname}(ChannelBuffer channelBuffer) {
        #{types2initializer(types)}
    }

    public #{method_classname}(#{types2parameters(types)}) {
        #{types2assignments(types)}
    }

    public UnsignedShort getMethodMethodId() {
        return METHOD_ID;
    }

    @Override
    protected String getMethodMethodName() {
        return METHOD_NAME;
    }

#{types2getters(types)}

    @Override
    protected Transmittable[] getTransmittables() {
        return all(#{types2references(types)});
    }

}
JAVA
  
end

def tree2factories t
  t.map do |k,v|
    first = t.keys.first == k
    <<-JAVA
        #{!first ? '} else ' : ''}if (classId.equals(#{k}.CLASS_ID)) {        

#{tree2factory(v)}
JAVA
  end.join("\n")
end

def tree2factory m
  (m.map do |e|
    <<-JAVA
            if (methodId.equals(#{e}.METHOD_ID))
                return new #{e}(channelBuffer);
JAVA
  end << "\t\t\tthrow new UnknownMethodException(classId, methodId);").join("\n")
end

def tree2imports t
  t.keys.map { |e| "import #{BASE_PACKAGE}.#{e.downcase}.*;" }.join("\n")  
end

method_factory = proc do
  <<-JAVA
package de.eeriedaily.namqp.v08.methods;

import de.eeriedaily.namqp.v08.framing.UnknownClassException;
import de.eeriedaily.namqp.v08.framing.UnknownMethodException;
#{tree2imports(tree)}
import de.eeriedaily.namqp.v08.types.UnsignedShort;
import org.jboss.netty.buffer.ChannelBuffer;

import javax.annotation.Generated;

/**
 * @author Joern Barthel <joern.barthel@acm.org>
 */
@Generated(value = "generator.rb")  
public final class MethodFactory {

    private MethodFactory() {
    }

    public static Method get(UnsignedShort classId, UnsignedShort methodId, ChannelBuffer channelBuffer) {

#{tree2factories(tree)}
        } else
            throw new UnknownClassException(classId);
        
    }

}  
JAVA
end

### begin parsing

xml = XmlSimple.xml_in('amqp-0.8.xml')

typemap = { 
  'longstr' => 'LongString',
  'shortstr' => 'ShortString',
  'table' => 'FieldTable',
  'short' => 'UnsignedShort',
  'long' => 'UnsignedLong',
  'longlong' => 'UnsignedLong',
  'bit' => 'Bit',
  'octet' => 'Octet'
}

domains = xml['domain'].inject({}) { |a,e| a[e.delete('name')] = e; a }

tree = Dictionary.new

deprecation = {
  'channel' => [ 'alert' ], 
  'access' => :all,
  'file' => :all,
  'stream' => :all,
  'dtx' => :all,
  'tunnel' => :all,
  'test' => :all
}

xml['class'].reject { |c| deprecation[c['name']] == :all }.each do |c|

  class_name = c['name']
  path = File.dirname(__FILE__) + "/../src/main/java/#{BASE_PACKAGE.split('.').join('/')}/#{class_name}"
  FileUtils.mkdir_p(path)
  doc = escape_doc(c['doc'])
  class_classname = class_name.capitalize
  class_index = c['index']

  set binding
  
  File.open("#{path}/#{class_classname}.java", 'w') { |f| f.write(klass.call) }
  
  method_factories = []
  
  class_methods = c['method'].reject do |m|
    deprecation[class_name] and deprecation[class_name].include?(m['name'])
  end.map do |m|

    method_name = m['name']
    
    doc = escape_doc(m['doc'])
    method_classname = class_classname + method_name.split('-').map { |e| e.capitalize }.join
    method_index = m['index']

    tree[class_classname] = (tree[class_classname] ||= []) << method_classname if (!method_classname.end_with?('Ok'))

    # instance-variable -> type, doc, ...
    types = m['field'] ? m['field'].inject(Dictionary.new) do |a,e|
      
      name = escape_method_name(e.delete('name'))

      a[name] = e
      a[name]['type'] = typemap[e['type'] || domains[e['domain']]['type']] || raise("Unknown type in #{e['type']} #{method_classname}")

      a

    end : {}

    set binding
    
    File.open("#{path}/#{method_classname}.java", 'w') { |f| f.write(method.call) }
  
  end

end

path = File.dirname(__FILE__) + "/../src/main/java/#{BASE_PACKAGE.split('.').join('/')}/MethodFactory.java"
File.open(path, 'w') { |f| f.write(method_factory.call) }