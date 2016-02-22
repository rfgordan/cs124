
JCC = javac

JFLAGS = -Xlint -g

default: RandMst.class


RandMst.class: RandMst.java
	$(JCC) $(JFLAGS) RandMst.java

clean:
	$(RM) *.class