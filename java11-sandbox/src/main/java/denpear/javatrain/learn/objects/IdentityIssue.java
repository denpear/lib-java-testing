package denpear.javatrain.learn.objects;

/**
 * Java определяет два различных понятия тождественности:
 * идентичность экземпляров (грубо говоря, совпадение адресов в памяти;
 * проверяется как a == b);
 * равенство экземпляров, определяемое методом equals() (также называется
 * равенством по значению).
 * <p>
 * <p>
 * Что такое пул строк?
 * <p>
 * Пул строк – это набор строк, хранящийся в Heap.
 * <p>
 * ☕️ Пул строк возможен благодаря неизменяемости строк в Java и реализации идеи интернирования строк;
 * ☕️ Пул строк помогает экономить память, но по этой же причине создание строки занимает больше времени;
 * ☕️ Когда для создания строки используются ", то сначала ищется строка в пуле с таким же значением, если находится, то просто возвращается ссылка, иначе создается новая строка в пуле, а затем возвращается ссылка на неё;
 * ☕️ При использовании оператора new создаётся новый объект String. Затем при помощи метода intern() эту строку можно поместить в пул или же получить из пула ссылку на другой объект String с таким же значением;
 * ☕️ Пул строк является примером паттерна «Приспособленец» (Flyweight).
 */
public class IdentityIssue {
    public static void main(String[] args) {

        Integer integer1 = new Integer(45);
        Integer integer2 = new Integer(45);
        System.out.println(integer1 == integer2); //false
        System.out.println(integer1.equals(integer2)); //true - т.к. состояние обектов объективно  одинаковое

        String string1 = "a"; // механизм интернирования?
        String string2 = "a";
        System.out.println(string1 == string2); //true - т.к. береться из пула строк, вель мы не используем ключевое слово new String
        System.out.println(string1.equals(string2)); //true - т.к. состояние обектов объективно  одинаковое


    }


}
