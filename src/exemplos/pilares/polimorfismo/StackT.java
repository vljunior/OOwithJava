package exemplos.pilares.polimorfismo;

/*public*/ class Stack<T> {

    private T[] stackArray;
    private int top;
    private int capacity;

    /**
     * Inicializa a pilha com uma capacidade específica.
     * @param capacity A capacidade máxima da pilha.
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity;
        this.stackArray = (T[]) new Object[capacity];
        this.top = -1; // -1 indica que a pilha está vazia
    }

    /**
     * Adiciona um elemento ao topo da pilha.
     * @param element O elemento a ser adicionado.
     * @throws IllegalStateException se a pilha estiver cheia (overflow).
     */
    public void push(T element) {
        if (isFull()) {
            throw new IllegalStateException("Stack overflow");
        }
        stackArray[++top] = element;
    }

    /**
     * Remove e retorna o elemento no topo da pilha.
     * @return O elemento removido.
     * @throws IllegalStateException se a pilha estiver vazia.
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top--];
    }

    /**
     * Retorna o elemento no topo da pilha sem removê-lo.
     * @return O elemento no topo da pilha.
     * @throws IllegalStateException se a pilha estiver vazia.
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top];
    }

    /**
     * Verifica se a pilha está vazia.
     * @return true se a pilha não contiver elementos, caso contrário false.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Verifica se a pilha está cheia.
     * @return true se a pilha atingiu sua capacidade máxima, caso contrário false.
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * Retorna o número de elementos atualmente na pilha.
     * @return O tamanho atual da pilha.
     */
    public int size() {
        return top + 1;
    }
}

// Exemplo de uso
public class StackT {
    public static void main(String[] args) {
        // Criando uma pilha de Strings com capacidade 5
        Stack<String> stringStack = new Stack<>(5);

        System.out.println("Pilha vazia? " + stringStack.isEmpty()); // true

        // Adicionando elementos
        stringStack.push("Primeiro");
        stringStack.push("Segundo");
        stringStack.push("Terceiro");

        System.out.println("Tamanho da pilha: " + stringStack.size()); // 3
        System.out.println("Elemento no topo (peek): " + stringStack.peek()); // Terceiro

        // Removendo elementos
        System.out.println("Removido: " + stringStack.pop()); // Terceiro
        System.out.println("Tamanho após pop: " + stringStack.size()); // 2

        // Adicionando mais elementos para testar overflow
        stringStack.push("Quarto");
        stringStack.push("Quinto");
        stringStack.push("Sexto");

        System.out.println("Pilha cheia? " + stringStack.isFull()); // true

        try {
            stringStack.push("Sétimo");
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage()); // Stack overflow
        }
    }
}