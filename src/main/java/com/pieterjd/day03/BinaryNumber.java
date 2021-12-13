package com.pieterjd.day03;

import lombok.Getter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class BinaryNumber {
    private List<Integer> digits;

    public BinaryNumber() {
        digits = new ArrayList<>();
    }

    public BinaryNumber(String input){
        this();
        for(int i=0;i<input.length();i++){
         digits.add(Integer.parseInt(""+input.charAt(i)));
        }
    }
    public int toDecimal(){
        int decimal = 0;
        for(int i=getDigits().size()-1;i>=0;i--){
            decimal += getDigits().get(i) * Math.pow(2,getDigits().size()-1-i);
        }
        return decimal;
    }

    public BinaryNumber inverse(){
        BinaryNumber bn = new BinaryNumber();
        getDigits().forEach(d ->bn.add(1-d));
        return bn;
    }

    public String toString(){
        return digits.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining());
    }
    public int size() {
        return digits.size();
    }

    public boolean isEmpty() {
        return digits.isEmpty();
    }

    public boolean contains(Object o) {
        return digits.contains(o);
    }

    public Iterator<Integer> iterator() {
        return digits.iterator();
    }

    public Object[] toArray() {
        return digits.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return digits.toArray(a);
    }

    public boolean add(Integer integer) {
        return digits.add(integer);
    }

    public boolean remove(Object o) {
        return digits.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return digits.containsAll(c);
    }

    public boolean addAll(Collection<? extends Integer> c) {
        return digits.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Integer> c) {
        return digits.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return digits.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return digits.retainAll(c);
    }

    public void replaceAll(UnaryOperator<Integer> operator) {
        digits.replaceAll(operator);
    }

    public void sort(Comparator<? super Integer> c) {
        digits.sort(c);
    }

    public void clear() {
        digits.clear();
    }

    public Integer get(int index) {
        return digits.get(index);
    }

    public Integer set(int index, Integer element) {
        return digits.set(index, element);
    }

    public void add(int index, Integer element) {
        digits.add(index, element);
    }

    public Integer remove(int index) {
        return digits.remove(index);
    }

    public int indexOf(Object o) {
        return digits.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return digits.lastIndexOf(o);
    }

    public ListIterator<Integer> listIterator() {
        return digits.listIterator();
    }

    public ListIterator<Integer> listIterator(int index) {
        return digits.listIterator(index);
    }

    public List<Integer> subList(int fromIndex, int toIndex) {
        return digits.subList(fromIndex, toIndex);
    }

    public Spliterator<Integer> spliterator() {
        return digits.spliterator();
    }

    public boolean removeIf(Predicate<? super Integer> filter) {
        return digits.removeIf(filter);
    }

    public Stream<Integer> stream() {
        return digits.stream();
    }

    public Stream<Integer> parallelStream() {
        return digits.parallelStream();
    }

    public void forEach(Consumer<? super Integer> action) {
        digits.forEach(action);
    }
}
