JAVASRC		= ConsoleFlood.java Board.java
SOURCES 	= ${JAVASRC} Makefile
MAINCLASS	= ConsoleFlood
CLASSES		= ${patsubst %.java, %.class, ${JAVASRC}}
INNCLASSES	= Board\$$Row.class Board\$$Index.class 
JARCLASSES 	= ${CLASSES} ${INNCLASSES}
JARFILE		= ConsoleFlood

all : ${JARFILE}

${JARFILE} : ${CLASSES}
	echo Main-class: ${MAINCLASS} >Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	chmod +x ${JARFILE}
	- rm Manifest

%.class : %.java
	javac -Xlint $<
clean :
	- rm ${JARCLASSES} Manifest

spotless : clean
	- rm ${JARFILE}
