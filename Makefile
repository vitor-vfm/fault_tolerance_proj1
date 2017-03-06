all:
	javac -g *.java
	javah InsertionSort
	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fpic -o libinsertionsort.so insertionsort.c

clean:
	rm -f *.class
	rm -f *.o
	rm -f *.so
