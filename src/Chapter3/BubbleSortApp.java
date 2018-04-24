package Chapter3;

// bubbleSort.java
import java.util.Random;
class ArrayClass implements Cloneable
{
private long[] a; 
private int nElems; 
int nSwaps; 
//--------------------------------------------------------------
public ArrayClass(int max) 
{
a = new long[max];
nElems = 0; 
}
//--------------------------------------------------------------
public long[] getArray() { return a;  }
public void setArray(long[] another) { a = another; }
//--------------------------------------------------------------
public ArrayClass (ArrayClass other) // копировать все поля класса тут
{     this.nElems = other.nElems; // примитивные типы передаются by value
      this.a = other.getArray().clone(); // для остальных используем их конструкторы копий
}
//--------------------------------------------------------------
 public ArrayClass copy() 
 {
           return new ArrayClass(this);
 }
//--------------------------------------------------------------
public void insert(long value) 
{
a[nElems] = value; 
nElems++;
}
//--------------------------------------------------------------
public void insertBack(long N) // вставка по уменьшению
{
long k = N-1;
	for (int i = 0; i<N; i++){
	a[i] = k; 
	k--;
	nElems++;
	}
}
//--------------------------------------------------------------
public void insertForw(long N) // вставка по возрастанию
{
long k = 0;
	for (int i = 0; i<N; i++){
	a[i] = k; 
	k++;
	nElems++;
	}
}
//--------------------------------------------------------------
public void display() 
{
for(int j=0; j<nElems; j++) 
System.out.print(a[j] + " "); 
System.out.println("");
}
//--------------------------------------------------------------
public void bubbleSort()
{
int out, in;
for(out=nElems-1; out>1; out--) 
for(in=0; in<out; in++) 
if( a[in] > a[in+1] ) 
swap(in, in+1); 
}
//--------------------------------------------------------------
public void selectionSort()
{
int out, in, min;
for(out=0; out<nElems-1; out++) // Внешний цикл
{
min = out; // Минимум
for(in=out+1; in<nElems; in++) // Внутренний цикл
if(a[in] < a[min] ) // Если значение min больше,
min = in; // значит, найден новый минимум
swap(out, min); // Поменять их местами
}
}
//--------------------------------------------------------------
public void insertionSort()
{
int in, out;
for(out=1; out<nElems; out++) // out - разделительный маркер
{
long temp = a[out]; // Скопировать помеченный элемент
in = out; // Начать перемещения с out
while(in>0 && a[in-1] >= temp) // Пока не найден меньший элемент
{
a[in] = a[in-1]; // Сдвинуть элемент вправо
--in; // Перейти на одну позицию влево
}
a[in] = temp; // Вставить помеченный элемент
}
}
//--------------------------------------------------------------
private void swap(int one, int two)
{
long temp = a[one];
a[one] = a[two];
a[two] = temp;
nSwaps++;
}
//--------------------------------------------------------------
public long FindMin(int k) { // нахождение k наименьшего
	int LeftB, RightB, i, j;
        long x, w;
	LeftB = 0;
	RightB = nElems-1;
	while (LeftB < RightB) {
            x = a[k];
            i = LeftB;
            j = RightB;
            do {
		while (x > a[i]) i++;
		while (x < a[j]) j--;
		if (i <= j) {
                    w = a[i];
                    a[i] = a[j];
                    a[j] = w;
                    i++;
                    j--;
		}
            } while (i < j);
            if (k > j) LeftB = i;
            if (k < i) RightB = j;
	}
        return a[k];
}
//--------------------------------------------------------------
public void randomArrayNoDups(int size){ // создание случайного массива без повторов
    size++;
    Random r = new Random();
    int[] list = new int[size];
    for (int i = 0; i < size; ++i) list[i] = i;
    int number;
    int index = size - 1;
    while (index > 0)
        {
         number = r.nextInt(index);
         insert(list[number]);
         list[number] = list[index];
         --index;
        }
    }
//--------------------------------------------------------------
public void noDups() {  // удаление дублей
int k, v;
int n = 0;
long[] temp = a;
int tempElems = nElems;
for (int i=0; i<tempElems; i++){
    a[n] = temp[i];
    n++;
    k = i;
    v = i+1;
    while ((v<tempElems)&&(temp[k]==temp[v])){
      v++;
      i++;
      nElems--;      
    }
}
}
//--------------------------------------------------------------
}
class BubbleSortApp
{
public static void main(String[] args)
{
long start;
long timeWorkBub, timeWorkIns, timeWorkSel;
int min; // нахождение k наименьшего в массиве
int maxSize = 20; //  115000 примерно 30 секунд для сортировки методом вставки
ArrayClass arrBub = new ArrayClass(maxSize);
for(int j=0; j<maxSize; j++) // Заполнение массива случайными числами
{
    long n = (long)( java.lang.Math.random()*(maxSize) );
    arrBub.insert(n);
} 
arrBub.insertionSort();
arrBub.display();
arrBub.noDups();
arrBub.display();

/*
ArrayClass arrSel = arrBub.copy();
ArrayClass arrIns = arrBub.copy();

start = System.currentTimeMillis();
arrBub.bubbleSort(); 
timeWorkBub = System.currentTimeMillis() - start;

start = System.currentTimeMillis();
arrSel.selectionSort();
timeWorkSel = System.currentTimeMillis() - start;

start = System.currentTimeMillis();
arrIns.insertionSort();
timeWorkIns = System.currentTimeMillis() - start;

System.out.println("Время пузырьковой сортировки: " + timeWorkBub/1000 + " секунд");
System.out.println("Время сортировки методом выбора: " + timeWorkSel/1000 + " секунд");
System.out.println("Время сортировки методом вставки: " + timeWorkIns/1000 + " секунд");
//System.out.println("Количество перестановок: " + arr.nSwaps);
*/
}
}