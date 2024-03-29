package denpear.javatrain.learn.objects;

/**
 * Java определяет два различных понятия тождественности: идентичность экземпляров (грубо говоря, совпадение адресов в
 * памяти; проверяется как a == b); равенство экземпляров, определяемое методом equals() (также называется равенством по
 * значению).
 * <p>
 * <p>
 * Что такое пул строк?
 * <p>
 * Пул строк – это набор строк, хранящийся в Heap.
 * <p>
 * ☕️ Пул строк возможен благодаря неизменяемости строк в Java и реализации идеи интернирования строк; ☕️ Пул строк
 * помогает экономить память, но по этой же причине создание строки занимает больше времени; ☕️ Когда для создания
 * строки используются ", то сначала ищется строка в пуле с таким же значением, если находится, то просто возвращается
 * ссылка, иначе создается новая строка в пуле, а затем возвращается ссылка на неё; ☕️ При использовании оператора new
 * создаётся новый объект String. Затем при помощи метода intern() эту строку можно поместить в пул или же получить из
 * пула ссылку на другой объект String с таким же значением; ☕️ Пул строк является примером паттерна «Приспособленец»
 * (Flyweight).
 */
public class IdentityIssue {
    public static void main(String[] args) {

        Integer integer1 = new Integer(45);
        Integer integer2 = new Integer(45);
        System.out.println(integer1 == integer2); //false
        System.out.println(integer1.equals(integer2)); //true - т.к. состояние обектов объективно  одинаковое

        Integer integer3 = 48;
        Integer integer4 = 48;
        System.out.println(integer3 == integer4); //false
        System.out.println(integer3.equals(integer4));

        String string1 = "a"; // механизм интернирования?
        String string2 = "a";
        System.out.println(string1
                == string2); //true - т.к. береться из пула строк, ведь мы не используем ключевое слово new для создания объекта String
        System.out.println(string1.equals(string2)); //true - т.к. состояние обектов объективно  одинаковое

        int i = 0;
        i = i++;
        System.out.println(String.valueOf(i));
        /**
         * Операторы инкремента и декремента ++ и -- соответственно могут применяться к числовым
         * операндам и иметь более высокий порядок или приоритет по сравнению с двоичными операторами.
         * Другими словами, они часто сначала применяются к выражению.
         * Операторы инкремента и декремента требуют особой осторожности, поскольку порядок, в котором они
         * применяются к связанному с ними операнду, может повлиять на то, как обрабатывается выражение.
         * Если оператор помещается перед операндом, называемым оператором предварительного увеличения
         * и оператором предварительного уменьшения, то сначала применяется оператор и возвращается значение
         * является новым значением выражения. Альтернативно, если оператор помещается после операнда,
         * называемого оператором постинкремента и оператором пост-декремента, то возвращается исходное
         * значение выражения, при этом оператор применяется после возврата значения.
         *
         */

        int x = 3;
        int y = ++x * 5 / x-- + --x;
        /**
         * int y = 4 * 5 / x-- + --x; // x assigned value of 4
         * int y = 4 * 5 / 4 + --x; // x assigned value of 3
         * int y = 4 * 5 / 4 + 2; // x assigned value of 2
         */
        System.out.println("x is " + x);
        System.out.println("y is " + y);


    }


}
