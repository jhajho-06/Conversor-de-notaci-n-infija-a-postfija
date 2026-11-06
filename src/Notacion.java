import java.util.Stack;

public class Notacion {

    private static int prioridad(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1; 
            case '*':
            case '/':
                return 2; 
            default:
                return -1;
        }
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean esDigito(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static String validarExpresion(String cadena) {
        if (cadena == null || cadena.trim().isEmpty()) {
            return "La cadena esta vacia o solo contiene espacios.";
        }

        String limpiar = cadena.replaceAll("\\s+", ""); // Eliminar espacios para facilitar el análisis sintáctico

        int balanceParentesis = 0;
        char ultimoChar = ' ';

        for (int i = 0; i < limpiar.length(); i++) {
            char c = limpiar.charAt(i);
            if (!esDigito(c) && !esOperador(c) && c != '(' && c != ')') {
                return "Caracter no permitido encontrado: '" + c + "'";
            }
            if (c == ')' && ultimoChar == '(') {
                return "Se encontro un parentesis vacio '()'.";
            }
            if (c == '(') {
                balanceParentesis++;
            } else if (c == ')') {
                balanceParentesis--;
                if (balanceParentesis < 0) {
                    return "Parentesis desbalanceados (hay un cierre sin apertura).";
                }
            }

            if (esOperador(c)) {
                if (i == 0 || i == limpiar.length() - 1) {
                    return "Operador al inicio o al final de la expresion.";
                }
                if (esOperador(ultimoChar)) {
                    return "Dos operadores seguidos: '" + ultimoChar + c + "'";
                }
            }

            if (esDigito(c)) {
                if (esDigito(ultimoChar)) {
                    return "Dos operandos seguidos sin operador: '" + ultimoChar + c + "'";
                }
            }

            ultimoChar = c;
        }

        if (balanceParentesis != 0) {
            return "Parentesis desbalanceados (faltan parentesis de cierre).";
        }

        return null;
    }

    public static void convertir(String cadenaOriginal) {
        String error = validarExpresion(cadenaOriginal);
        if (error != null) {
            System.out.println("Error de validacion: " + error);
            return;
        }

        String cadena = cadenaOriginal.replaceAll("\\s+", "");
        StringBuilder salida = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        System.out.println("Expresion Infija: " + cadenaOriginal);
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if (esDigito(c)) {
                salida.append(c);
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    salida.append(pila.pop());
                }
                if (!pila.isEmpty()) {
                    pila.pop();
                }
            } else if (esOperador(c)) {
                while (!pila.isEmpty() && prioridad(c) <= prioridad(pila.peek())) {
                    salida.append(pila.pop());
                }
                pila.push(c);
            }

        }

        while (!pila.isEmpty()) {
            salida.append(pila.pop());
        }

        System.out.println("Expresion Postfija final: " + salida.toString());
    }
}