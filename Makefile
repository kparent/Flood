JAVASRC		= Flood.java Game.java Board.java UI.java
SOURCES 	= ${JAVASRC} Makefile
MAINCLASS	= Flood
CLASSES		= ${patsubst %.java, %.class, ${JAVASRC}}
INNCLASSES	= Board\$$Node.class UI\$$Button.class
JARCLASSES 	= ${CLASSES} ${INNCLASSES}
JARFILE		= Flood

all : ${JARFILE}

${JARFILE} : ${CLASSES}
	echo Main-class: ${MAINCLASS} >Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	chmod +x ${JARFILE}
	- rm Manifest

%.class : %.java
	javac -Xlint $<
clean :
	- rm *.class Manifest

spotless : clean
	- rm ${JARFILE}
