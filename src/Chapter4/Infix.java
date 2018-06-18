package Chapter4;
import java.io.*; // Для ввода/вывода
class StackX {
    private int maxSize;
    private char[] stackArray;
    private int top;
//--------------------------------------------------------------
    public StackX(int s) {// Конструктор
        maxSize = s;
        stackArray = new char[maxSize];
        top = -1;
    }
//--------------------------------------------------------------
    public void push(char j) // Размещение элемента на вершине стека
    { stackArray[++top] = j; }
    //--------------------------------------------------------------
    public char pop() // Извлечение элемента с вершины стека
    { return stackArray[top--]; }
    //--------------------------------------------------------------
    public char peek() // Чтение элемента с вершины стека
    { return stackArray[top]; }
    //--------------------------------------------------------------
    public boolean isEmpty() // true, если стек пуст
    { return (top == -1); }
    //-------------------------------------------------------------
    public int size() // Текущий размер стека
    { return top+1; }
    //--------------------------------------------------------------
    public char peekN(int n) // Чтение элемента с индексом n
    { return stackArray[n]; }
    //--------------------------------------------------------------
    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for(int j=0; j<size(); j++) {
            System.out.print( peekN(j) );
            System.out.print(' ');
        }
        System.out.println("");
    }
//--------------------------------------------------------------
} // Конец класса StackX
////////////////////////////////////////////////////////////////
class InToPost { // Преобразование инфиксной записи в постфиксную
    private StackX theStack;
    private String input;
    private String output = "";
//--------------------------------------------------------------
    public InToPost(String in) { // Конструктор
        input = in;
        int stackSize = input.length();
        theStack = new StackX(stackSize);
    }
//--------------------------------------------------------------
    public String doTrans() { // Преобразование в постфиксную форму
        for(int j=0; j<input.length(); j++) {
            char ch = input.charAt(j);
            theStack.displayStack("For "+ch+" "); // *диагностика*
            switch(ch) {
                case '+': // + или -
                case '-':
                gotOper(ch, 1); // Извлечение операторов (приоритет 1)
                break; 
                case '*': // * или /
                case '/':
                gotOper(ch, 2); // Извлечение операторов (приоритет 2)
                break;
                case '(': // Открывающая круглая скобка
                theStack.push(ch); // Занести в стек
                break;
                case ')': // Закрывающая круглая скобка
                gotParen(ch); // Извлечение операторов
                break;
                default: // Остается операнд
                output = output + ch; // Записать в выходную строку
                break;
            }
        }
        while( !theStack.isEmpty() ) { // Извлечение оставшихся операторов
            theStack.displayStack("While "); // *диагностика*
            output = output + theStack.pop(); // write to output
        }
        theStack.displayStack("End "); // *диагностика*
        return output; // Возвращение постфиксного выражения
    }
//--------------------------------------------------------------
    public void gotOper(char opThis, int prec1) { // Чтение оператора из входной строки
        while( !theStack.isEmpty() ) {
            char opTop = theStack.pop();
            if( opTop == '(' ) { // Если это '('
                theStack.push(opTop); // Вернуть '('
                break;
            } else {
                int prec2; // Приоритет нового оператора 
                if(opTop=='+' || opTop=='-') prec2 = 1; else prec2 = 2; // Определение приоритета
                if(prec2 < prec1) { // Если приоритет нового оператора меньше приоритета старого
                    theStack.push(opTop); // Сохранить новый оператор
                    break;
                } else output = output + opTop; // Приоритет нового оператора
            }
        }
        theStack.push(opThis); // Занесение в стек нового оператора
    }
//-------------------------------------------------------------- 
    public void gotParen(char ch) { // Прочитана закрывающая скобка 
        while( !theStack.isEmpty() ) { 
            char chx = theStack.pop();
            if( chx == '(' ) // Если извлечен элемент ‘(‘
                break; // Прервать выполнение
            else // Если извлечен оператор 
                output = output + chx; // Вывести в постфиксную строку
        }
    }
//--------------------------------------------------------------
} // Конец класса InToPost
////////////////////////////////////////////////////////////////
class InfixApp {
    public static void main(String[] args) throws IOException {
        String input, output;
        while(true) {
            System.out.print("Enter infix: ");
            System.out.flush();
            input = getString(); // Чтение строки с клавиатуры
            if( input.equals("") ) // Выход, если нажата клавиша [Enter]
                break; 
            // Создание объекта-преобразователя
            InToPost theTrans = new InToPost(input); 
            output = theTrans.doTrans(); // Преобразование
            System.out.println("Postfix is " + output + '\n');
        }
    }
//--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
} // Конец класса InfixApp