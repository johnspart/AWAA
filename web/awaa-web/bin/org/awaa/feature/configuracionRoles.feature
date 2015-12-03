# encoding: iso-8859-1
Feature: Visualizacion de inico 
Background: 
	Given El usuario accede a awaa 
	
Scenario Outline: El usuario crea un nuevo Rol 
	Given El usuario accede a Roles 
	When Presione el boton Nuevo Rol 
	And Ingresa el nombre del rol <rol> 
	And Ingresa la descripcion <descripcion> 
	And Presiona guardar 
	Then Visualiza el rol creado en la lista 
	
	Examples: 
		| rol | descripcion |
		|"rTest"|"Test de prueba"|
		|"rTest2"|"Test de prueba 2"|
		