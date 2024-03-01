//menu principal
#include "menu.hpp"
#include "func_prog.hpp"

int main(void){
    Menu menu;
    Pacientes pacientes;
    Medicos medicos;
    Consultas consultas;

    pacientes.inserePaciente(new Paciente((string)"111.222.333-00", (string)"Jhonata",  Data(10,10,2023)));
    pacientes.inserePaciente(new Paciente((string)"222.333.444-00", (string)"Igor Alex",  Data(13,10,2022)));
    pacientes.inserePaciente(new Paciente((string)"333.444.555-00", (string)"Daniel Penedo",  Data(10,11,2023)));

    medicos.insereMedico(new Medico((string)"12345678-9/BA", (string)"David",  string("Cardiologista")));
    medicos.insereMedico(new Medico((string)"98765432-1/BA", (string)"Ian Robert",  string("Dermatologista")));
    medicos.insereMedico(new Medico((string)"00000000-0/SP", (string)"Maria Silva",  string("Psicologa")));


    Consulta consulta1(DataHora(), Hora(), "Plansul", pacientes.obterListaPacientes().at(0), medicos.obterListaMedicos().at(0));
    Consulta consulta2(DataHora(), Hora(), "Bradesco Saude", pacientes.obterListaPacientes().at(1), medicos.obterListaMedicos().at(1));
    Consulta consulta3(DataHora(), Hora(), "Sem Convenio", pacientes.obterListaPacientes().at(2), medicos.obterListaMedicos().at(0));
    consultas.insereConsulta(&consulta1);
    consultas.insereConsulta(&consulta2);
    consultas.insereConsulta(&consulta3);

    while(true)
    {
        menu.mostra_menu();
        int opcao = menu.obter_opcao();

        switch (opcao){

            case 0: exit(0);
                    break; 
            
            case 1: abrirMenuPaciente(&pacientes);
                    break;
            
            case 2: abrirMenuMedico(&medicos);
                    break;        

            case 3: abrirMenuConsultas(&consultas, &pacientes, &medicos);
                    break;

            default:
                break;
        }
    }

    return 0;
}