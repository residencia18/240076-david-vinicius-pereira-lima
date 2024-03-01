//cabecalho das funcoes do programa principal
#include <iostream>
#include <string>
#include <vector>
#include <conio.h>
#include "func_prog_pac.hpp"
#include "func_prog_med.hpp"
#include "func_prog_con.hpp"


using namespace std;

void abrirMenuPaciente(Pacientes * pacientes);
void abrirMenuMedico(Medicos * medicos);
void abrirMenuConsultas(Consultas * consultas, Pacientes * pacientes, Medicos * medicos);
