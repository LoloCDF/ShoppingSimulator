##################################
# Compila y ejecuta              #
##################################

runandcomp:
	make comp && make exe

##################################
# Compila ShoppingSimulator      #
##################################

comp:
	find src/ -name "*.java" -print | xargs javac -d build
	jar cvfe ShoppingSimulator.jar ShoppingSimulator.Main -C build ShoppingSimulator

##################################
# Ejecuta ShoppingSimulator      #
##################################
exe:
	java -jar ShoppingSimulator.jar
