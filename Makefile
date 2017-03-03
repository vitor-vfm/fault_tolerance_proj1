all:
	javac -g *.java
	gcc -g insertion_sort.c -o is

clean:
	rm -f *.class
