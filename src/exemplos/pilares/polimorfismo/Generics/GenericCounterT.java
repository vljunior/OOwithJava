package exemplos.pilares.polimorfismo.Generics;

/*public*/ class GenericCounter<T extends Number> {

    private T value;

    public GenericCounter(T initialValue) {
        if (initialValue == null) {
            throw new IllegalArgumentException("O valor inicial não pode ser nulo.");
        }
        this.value = initialValue;
    }

    public void increment() {
        if (value instanceof Integer) {
            this.value = (T) Integer.valueOf(value.intValue() + 1);
        } else if (value instanceof Float) {
            this.value = (T) Float.valueOf(value.floatValue() + 1.0f);
        } else if (value instanceof Double) {
            this.value = (T) Double.valueOf(value.doubleValue() + 1.0);
        } else {
            throw new UnsupportedOperationException("Tipo de dado não suportado para incremento/decremento.");
        }
    }

    public void decrement() {
        if (value instanceof Integer) {
            this.value = (T) Integer.valueOf(value.intValue() - 1);
        } else if (value instanceof Float) {
            this.value = (T) Float.valueOf(value.floatValue() - 1.0f);
        } else if (value instanceof Double) {
            this.value = (T) Double.valueOf(value.doubleValue() - 1.0);
        } else {
            throw new UnsupportedOperationException("Tipo de dado não suportado para incremento/decremento.");
        }
    }

    public void add(T amount) {
        if (amount == null) {
            return;
        }

        if (value instanceof Integer) {
            this.value = (T) Integer.valueOf(value.intValue() + amount.intValue());
        } else if (value instanceof Float) {
            this.value = (T) Float.valueOf(value.floatValue() + amount.floatValue());
        } else if (value instanceof Double) {
            this.value = (T) Double.valueOf(value.doubleValue() + amount.doubleValue());
        } else {
            throw new UnsupportedOperationException("Tipo de dado não suportado para acumulação.");
        }
    }

    public void subtract(T amount) {
        if (amount == null) {
            return;
        }

        if (value instanceof Integer) {
            this.value = (T) Integer.valueOf(value.intValue() - amount.intValue());
        } else if (value instanceof Float) {
            this.value = (T) Float.valueOf(value.floatValue() - amount.floatValue());
        } else if (value instanceof Double) {
            this.value = (T) Double.valueOf(value.doubleValue() - amount.doubleValue());
        } else {
            throw new UnsupportedOperationException("Tipo de dado não suportado para decremento de valor.");
        }
    }

    public T getValue() {
        return value;
    }

    public void reset(T initialValue) {
        if (initialValue == null) {
            throw new IllegalArgumentException("O valor inicial não pode ser nulo.");
        }
        this.value = initialValue;
    }

    @Override
    public String toString() {
        return "GenericCounter{value=" + value + "}";
    }
}

public class GenericCounterT {
    public static void main(String[] args) {
        // Exemplo com Integer
        GenericCounter<Integer> intCounter = new GenericCounter<>(10);
        System.out.println("Valor inicial (int): " + intCounter.getValue());

        intCounter.increment();
        System.out.println("Após incrementar: " + intCounter.getValue());

        intCounter.decrement();
        System.out.println("Após decrementar: " + intCounter.getValue());

        intCounter.add(5);
        System.out.println("Após acumular 5: " + intCounter.getValue());

        intCounter.subtract(3);
        System.out.println("Após decrementar 3: " + intCounter.getValue());

        intCounter.reset(100);
        System.out.println("Após resetar: " + intCounter.getValue());

        System.out.println("--------------------");

        // Exemplo com Double
        GenericCounter<Double> doubleCounter = new GenericCounter<>(15.5);
        System.out.println("Valor inicial (double): " + doubleCounter.getValue());

        doubleCounter.increment();
        System.out.println("Após incrementar: " + doubleCounter.getValue());

        doubleCounter.add(4.5);
        System.out.println("Após acumular 4.5: " + doubleCounter.getValue());

        doubleCounter.subtract(10.0);
        System.out.println("Após decrementar 10.0: " + doubleCounter.getValue());
    }
}