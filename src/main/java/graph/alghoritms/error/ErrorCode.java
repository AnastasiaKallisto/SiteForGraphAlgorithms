package graph.alghoritms.error;

public enum ErrorCode {
    INCORRECT_JSON("Неверный JSON элемент"),
    NULL_VALUE("Значение NULL"),
    INVALID_NUMBER("Число задано неверно"),
    VOID_STRING("Пустая строка \"\" "),
    INCORRECT_REQUEST("Неверный запрос к серверу \"\" "),
    INVALID_N("Число вершин должно быть >= 3"),
    NO_GRAPH_NO_ALGORITHM("Граф не был задан, нельзя запустить алгоритм \"\" ");


    String errorString;
    ErrorCode(String s) {
        errorString = s;
    }

    public String getErrorString() {
        return errorString;
    }

}
