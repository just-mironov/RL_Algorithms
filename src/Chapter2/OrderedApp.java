package Chapter2;
// Работа с классом упорядоченного массива

import java.util.concurrent.ThreadLocalRandom;

class OrdArray
{
private long[] a; // Ссылка на массив a
private int nElems; // Количество элементов данных
//-----------------------------------------------------------
public OrdArray(int max) // Конструктор
{
a = new long[max]; // Создание массива
nElems = 0;
}
//-----------------------------------------------------------
public int size()
{ return nElems; }
//-----------------------------------------------------------
public int find(long searchKey)
{
int lowerBound = 0;
int upperBound = nElems-1;
int curIn;
while(true)
{
curIn = (lowerBound + upperBound ) / 2;
if(a[curIn]==searchKey)
return curIn; // Элемент найден
else if(lowerBound > upperBound)
return nElems; // Элемент не найден
else // Деление диапазона
{
if(a[curIn] < searchKey)
lowerBound = curIn + 1; // В верхней половине
else
upperBound = curIn - 1; // В нижней половине
}
}
}
//-----------------------------------------------------------
public void insert(long value) // Упорядочная вставка элемента в массив
{
int j;
for(j=0; j<nElems; j++) // Определение позиции вставки
if(a[j] > value) // (линейный поиск)
break;
for(int k=nElems; k>j; k--) // Перемещение последующих элементов
a[k] = a[k-1];
a[j] = value; // Вставка
nElems++; // Увеличение размера
}
public void fillRandom(int size) {	// упорядоченное заполнение массива случайными числами
int randomNum = 0;
for (int i=0; i<nElems; i++)
	randomNum = ThreadLocalRandom.current().nextInt(-100, 100 + 1);
	System.out.println(randomNum);
	/*a[i] = insertBin(randomNum);*/
}
//-----------------------------------------------------------
public void merge() {	// 
}
//-----------------------------------------------------------
public void insertBin(long value) // Упорядочная двоичная вставка элемента в массив
{
int lowerBound = 0;
int upperBound = nElems-1;
int curIn;
while(true)
{
curIn = (lowerBound + upperBound ) / 2;
if (lowerBound > upperBound) 	{
	if(a[curIn] < value) curIn++;
	for(int k=nElems; k>curIn; k--) // Перемещение последующих элементов
		a[k] = a[k-1];
	a[curIn] = value; // Вставка
	nElems++; // Увеличение размера
	break;
}
else // Деление диапазона
{
if(a[curIn] < value) 
lowerBound = curIn + 1; // В верхней половине
else
upperBound = curIn - 1; // В нижней половине
}
}
}
//-----------------------------------------------------------
public boolean delete(long value)
{
int j = find(value);
if(j==nElems) // Найти не удалось
return false;
else // Элемент найден
{
for(int k=j; k<nElems; k++) // Перемещение последующих элементов
a[k] = a[k+1];
nElems--; // Уменьшение размера
return true;
}
}
//-----------------------------------------------------------
public void display() // Вывод содержимого массива
{
for(int j=0; j<nElems; j++) // Перебор всех элементов
System.out.print(a[j] + " "); // Вывод текущего элемента
System.out.println("");
}
//-----------------------------------------------------------
}
////////////////////////////////////////////////////////////////
class OrderedApp
{
public static void main(String[] args)
{
/*
int maxSize = 100; // Размер массива
OrdArray arr; // Ссылка на массив
arr = new OrdArray(maxSize); // Создание массива
arr.insertBin(77); // Вставка 10 элементов
arr.insertBin(99);
arr.insertBin(44);
arr.insertBin(55);
arr.insertBin(22);
arr.insertBin(88);
arr.insertBin(11);
arr.insertBin(00);
arr.insertBin(20);
arr.display();
int searchKey = 55; // Поиск элемента
if( arr.find(searchKey) != arr.size() )
System.out.println("Found " + searchKey);
else
System.out.println("Can't find " + searchKey);
arr.display(); // Вывод содержимого
arr.delete(00); // Удаление трех элементов
arr.delete(55);
arr.delete(99);
arr.display(); // Повторный вывод
*/
int maxSize = 100; // Размер массива
OrdArray firstArr; // Ссылка на массив
firstArr = new OrdArray(maxSize); // Создание массива
firstArr.fillRandom(20);
firstArr.display();
}
} //Конец класса OrderedApp
////////////////////////////////////////////////////////////////
