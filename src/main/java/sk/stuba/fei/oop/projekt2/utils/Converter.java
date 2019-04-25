package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.generated.Document;

public abstract class Converter<T, R> {

    abstract T convert(R input);

}
