public enum TesterQuestionEnum {
    LINKED_LIST_ADD_FIRST("LinkedList class. addFirst", 6),
    LINKED_LIST_ADD_LAST("LinkedList class. addLast", 6),
    LINKED_LIST_GET_NODE("LinkedList class. getNode", 6),
    LINKED_LIST_GET_BLOCK("LinkedList class. getBlock", 6),
    LINKED_LIST_ADD("LinkedList class. add", 6),
    LINKED_LIST_INDEX_OF("LinkedList class. indexOf", 6),
    LINKED_LIST_REMOVE_NODE("LinkedList class. removeNode", 8),
    LINKED_LIST_REMOVE_ELEMENT("LinkedList class. removeElement", 8),
    LINKED_LIST_REMOVE_INDEX("LinkedList class. removeIndex", 8),
    MEMORY_SPACE_MALLOC("MemorySpace class. malloc", 15),
    MEMORY_SPACE_FREE("MemorySpace class. free", 15),
    MEMORY_SPACE_DEFRAG("MemorySpace class. defrag", 10);

    private final String question;
    private final int maxPoints;
    TesterQuestionEnum(String question, int maxPoints) {
        this.question = question;
        this.maxPoints = maxPoints;
    }

    public String getQuestion() {
        return this.question;
    }

    public int getMaxPoints() {
        return this.maxPoints;
    }
}