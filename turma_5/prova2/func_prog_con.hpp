//cabecalho das funcionalidades do menu de consultas
#include <iostream>
#include <string>
#include <vector>
#include "menuConsulta.hpp"
#include "classes.hpp"

using namespace std;

void incluirConsulta(Consultas * consultas, Pacientes * pacientes, Medicos * medicos);
void excluirConsulta(Consultas * Consultas, Medicos * medicos, Pacientes * pacientes);
void alterarConsulta(Consultas * consultas, Medicos * medicos, Pacientes * pacientes);
void listarConsultas(Consultas * consultas);

