package Chapter3;

// bubbleSort.java
import java.util.Random;
class ArrayClass implements Cloneable {
private long[] a; 
private int nElems; 
int nSwaps; 
public ArrayClass(int max) {
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
long k = N;
	for (int i = nElems; i<N; i++){
        nElems++;
	a[i] = k; 
	k--;
	}
}
//--------------------------------------------------------------
public void insertForw(long N) // вставка по возрастанию
{
long k = 0;
	for (int i = nElems; i<N; i++){
        nElems++;
	a[i] = k; 
	k++;
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
public void oddEvenSort() //сортировка методом четного/нечётного
{
boolean flag;
int n = 0;
do {
    flag = false;
    for (int i = 0; i < nElems; i+=2) if (a[i]>a[i+1]) {
        swap(i, i+1);
        flag = true;
    }
    for (int i = 1; i < nElems-1; i+=2) if (a[i]>a[i+1]) {
        swap(i, i+1);
        flag = true;
    }
    n++;
} while (flag);
  //  System.out.println("Количество проходов обработки: " + n);
}
//--------------------------------------------------------------
public void bubbleSort()  //пузырьковая сортировка
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
public void insertionSortOld() {
int in, out;
int copyCount = 0;
int compareCount = 0; 
for(out=1; out<nElems; out++) { // out - разделительный маркер
    long temp = a[out]; // Скопировать помеченный элемент
    in = out; // Начать перемещения с out
    while(in>0 && a[in-1] >= temp)  { // Пока не найден меньший элемент
       a[in] = a[in-1]; // Сдвинуть элемент вправо
       --in; // Перейти на одну позицию влево
    }
    a[in] = temp; // Вставить помеченный элемент
}
}
//--------------------------------------------------------------
public void noDupsByInsertion() {
int in, out;
for(out=1; out<nElems; out++) {
    long temp = a[out]; 
    for (in = out; in > 0; --in){
        if (a[in-1] == temp) {
            a[in-1] = -1;
            break;
        }
    }
}
insertionSortOld();
int j = 0;
long temp;
do temp = a[++j]; while (j<nElems&&temp==-1);
out = j;
for (int i = 0; j<nElems; i++, j++) a[i] = a[j];
nElems -= out;
}
//--------------------------------------------------------------
public int insertionSort() { //подсчитывает кол-во операций копирования и вставки
    int difficult = 0;    
    int copyCount = 0;
    int compareCount = 0;
    int in, out;
    for(out=1; out<nElems; out++) { // out - разделительный маркер
        long temp = a[out]; // Скопировать помеченный элемент
      //  copyCount++;
      //  compareCount++;
        for (in=out;  in > 0; --in) {
            compareCount++;
            if (a[in-1]>=temp) {
                a[in] = a[in-1];
                copyCount++;
            } else break;
        }
        a[in] = temp; // Вставить помеченный элемент
      //  copyCount++;
    }
    difficult = copyCount + compareCount;
    return difficult;
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
int k = 0;    
int i, n = 0;
for (i=0; i<nElems; i++){
    a[n] = a[i];
    while ((i<nElems-1)&&(a[n]==a[i+1])) {
        k++;
        i++;
    }
    n++;
}
nElems -= k;
}
//--------------------------------------------------------------
public void quickSort(){
    qSort(a, 0, nElems-1);
}
private void qSort (long[] A, int low, int high){
    int i = low;
    int j = high;
    long x = A[low+(high-low)/2];
      do {
         while(A[i] < x) ++i;
         while(A[j] > x) --j;
         if(i <= j){
            swap(i, j); i ++ ; j --;
         }
      } while(i <= j);
      //рекурсивные вызовы функции qSort
      if(low < j) qSort(A, low, j);
      if(i < high) qSort(A, i, high);
}
//--------------------------------------------------------------
}
class BubbleSortApp
{
public static void main(String[] args) {
    long startTime, timeInterval;
    int maxSize = 10; //  500 000 примерно 27 секунд для сортировки методом вставки
    ArrayClass arrBub = new ArrayClass(maxSize);
    for(int j=0; j<maxSize; j++) { // Заполнение массива случайными числами
        long n = (long)( java.lang.Math.random()*(maxSize) );
        arrBub.insert(n);
    }
//    ArrayClass arrSel = arrBub.copy();
//    ArrayClass arrIns = arrBub.copy();
//    ArrayClass oddEven = arrBub.copy();
//    ArrayClass arrQuick = arrBub.copy();

//  startTime = System.currentTimeMillis();
//  arrBub.bubbleSort(); 
//  timeInterval = System.currentTimeMillis() - startTime;
//  System.out.println("Время пузырьковой сортировки: " + timeInterval/1000 + " секунд");

//    startTime = System.currentTimeMillis();
//    arrSel.selectionSort();
//    timeInterval = System.currentTimeMillis() - startTime;
//    System.out.println("Время сортировки методом выбора: " + timeInterval/1000 + " секунд");
    
//    startTime = System.currentTimeMillis();
//    arrIns.insertionSort();
//    timeInterval = System.currentTimeMillis() - startTime;
//    System.out.println("Время сортировки методом вставки: " + timeInterval/1000 + " секунд");
    
//    startTime = System.currentTimeMillis();
//    oddEven.oddEvenSort(); 
//    timeInterval = System.currentTimeMillis() - startTime;
//    System.out.println("Время сортировки методом чётных/нечётных перестановок: " + timeInterval/1000 + " секунд");

//    startTime = System.currentTimeMillis();
//    arrBub.quickSort(); 
//    timeInterval = System.currentTimeMillis() - startTime;
//    System.out.println("Время методом QuickSort: " + timeInterval/1000 + " секунд");

    arrBub.display();
    arrBub.noDupsByInsertion();
    arrBub.display();
}
}