package sk.stuba.fei.oop.projekt2.utils.converters;

public abstract class Converter<T, R> {

    abstract T convert(R input);

}
