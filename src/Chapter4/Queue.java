package Chapter4;
// queue.java
// Работа с очередью
// Запуск программы: C>java QueueApp
////////////////////////////////////////////////////////////////
public class Queue {
private int maxSize;
private long[] queArray;
private int front;
private int rear;
private int nItems;
//--------------------------------------------------------------
public Queue(int s) // Конструктор
{
maxSize = s;
queArray = new long[maxSize];
front = 0;
rear = -1;
nItems = 0;
}
//--------------------------------------------------------------
public void insert(long j) // Вставка элемента в конец очереди
{
if(rear == maxSize-1) // Циклический перенос
rear = -1;
queArray[++rear] = j; // Увеличение rear и вставка
nItems++; // Увеличение количества элементов
}
//--------------------------------------------------------------
public long remove() // Извлечение элемента в начале очереди
{
long temp = queArray[front++]; // Выборка и увеличение front
if(front == maxSize) // Циклический перенос
front = 0;
nItems--; // Уменьшение количества элементов
return temp;
}
//--------------------------------------------------------------
public long peekFront() // Чтение элемента в начале очереди
{
return queArray[front];
}
//--------------------------------------------------------------
public boolean isEmpty() // true, если очередь пуста
{
return (nItems==0);
}
//--------------------------------------------------------------
public boolean isFull() // true, если очередь заполнена
{
return (nItems==maxSize);
}
//--------------------------------------------------------------
public int size() // Количество элементов в очереди
{
return nItems;
}
public void PrintQueue (){
    if (nItems == 0) {
        System.out.println("Очередь пуста"); 
        return;
    }
    if (rear > front) 
        for (int i = front; i <= rear; i++) 
            System.out.print(queArray[i] + " ");
    else if (rear < front){
        for (int i = front; i < nItems; i++)
            System.out.print(queArray[i]+ " ");
        for (int i = 0; i <= rear; i++)
            System.out.print(queArray[i]+ " ");
    } else System.out.print(queArray[rear]+ " ");
    System.out.println("");
}
//--------------------------------------------------------------
} // Конец класса Queue
////////////////////////////////////////////////////////////////
class QueueApp
{
public static void main(String[] args)
{
Queue theQueue = new Queue(10); // Очередь из 5 ячеек
theQueue.insert(10); // Вставка 4 элементов
theQueue.insert(20);
theQueue.insert(30);
theQueue.insert(40);
theQueue.insert(50);
theQueue.insert(60);
theQueue.insert(70);
theQueue.insert(80);
theQueue.insert(90);
theQueue.PrintQueue();
theQueue.remove(); // Извлечение 3 элементов
theQueue.remove(); // (10, 20, 30, 40, 50, 60)
theQueue.remove();
theQueue.remove();
theQueue.remove();
theQueue.remove();
theQueue.PrintQueue();
theQueue.insert(100); // Вставка еще 4 элементов
theQueue.insert(110); // (с циклическим переносом)
theQueue.insert(120);
theQueue.insert(130);
theQueue.insert(140);
theQueue.PrintQueue();
/* while( !theQueue.isEmpty() ) // Извлечение и вывод
{ // всех элементов
long n = theQueue.remove(); // (40, 50, 60, 70, 80)
System.out.print(n);
System.out.print(" ");
System.out.println("");
}
*/
}
} // Конец класса QueueApp