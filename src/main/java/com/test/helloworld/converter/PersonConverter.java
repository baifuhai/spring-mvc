package com.test.helloworld.converter;

import com.test.helloworld.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PersonConverter implements Converter<String, Person> {

    @Override
    public Person convert(String source) {
        String[] ss = source.split("-");
        return new Person(Integer.parseInt(ss[0]), ss[1]);
    }

}

@Component
class PersonConverter2 implements ConverterFactory<String, Person> {

    @Override
    public <T extends Person> Converter<String, T> getConverter(Class<T> targetType) {
        return null;
    }
}

@Component
class PersonConverter3 implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return null;
    }

}
