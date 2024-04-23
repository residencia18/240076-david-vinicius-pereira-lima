# Suniapp - Sistema de Controle de Suínos

Esse Projeto foi gerado com  [Angular CLI](https://github.com/angular/angular-cli) versão 17.2.3.

Este é um projeto Angular desenvolvido para gerenciar a criação de suínos em uma fazenda de suinocultura. A aplicação possui funcionalidades para cadastro de suínos, listagem, controle de peso, sessoões de trabalho e autenticação de usuários. 

## Funcionalidades

### Cadastro de Suíno
- Criação de um suíno com os seguintes campos:
  - Brinco do animal (número, obrigatório)
  - Brinco do pai (número, obrigatório)
  - Brinco da mãe (número, obrigatório)
  - Data de Nascimento (obrigatório)
  - Data de Saída (obrigatório)
  - Status (ativo, vendido ou morto)
  - Sexo (M ou F)

### Listagem de Suínos
- Lista todos os suínos da fazenda com opções de editar e deletar.
- Filtros disponíveis:
  - Brinco do pai
  - Brinco da mãe
  - Data de nascimento
  - Data de saída
  - Sexo
  - Status

### Controle de Peso
- Monitoramento do peso do animal com gráfico de histórico de pesos.
- Cadastro de peso com os campos:
  - Número do brinco do animal (já cadastrado)
  - Data da pesagem
  - Peso em Kg

### Sessões
- Gerenciamento de sessões de trabalho para os suinocultores.
- Cadastro de sessões com os seguintes campos:
  - Data da sessão
  - Horário de início
  - Horário de término
  - Atividades realizadas

### Autenticação do Usuário
- Login com validação de email e senha.
- Opção para nova inscrição.

## Recomendações para Desenvolvimento

- Utilize bibliotecas de componentes para melhorar a aparência da aplicação, como Angular Material ou PrimeNG.
- Implemente a infraestrutura do backend utilizando Firebase ou outra plataforma similar.
- Integre todos os componentes em um design profissional e limpo, considerando o uso de Flexbox Layout e/ou Grid Layout.

## Estrutura do Projeto

O projeto está estruturado com os seguintes componentes principais:

- **Cadastro de Suínos**: Componente para adicionar novos suínos.
- **Listagem de Suínos**: Componente para visualizar e filtrar suínos existentes.
- **Controle de Peso**: Componentes para monitorar o peso dos suínos e adicionar novos registros de peso.
- **Sessões**: Componentes para gerenciar sessões de trabalho.
- **Autenticação**: Componente para login de usuários e opção de nova inscrição.
- **Serviço de Backend**: Comunicação com o backend (Firebase) para persistência de dados.

## Como Executar

1. Clone o repositório.
2. Instale as dependências com `npm install`.
3. **Importante:** Crie um arquivo `app.config.ts` na pasta `src/app/` e adicione o seguinte conteúdo:
```typescript
// src/app/app.config.ts

export const config = {
  firebaseURL: 'URL_DO_HOSTING_DO_FIREBASE',
  projectAPIKey: 'API_KEY_DO_FIREBASE',
};
```
4. Atualize os valores `'URL_DO_HOSTING_DO_FIREBASE'` e `'API_KEY_DO_FIREBASE'` com as informações corretas para o Firebase ou sua API.
5. Execute a aplicação com `ng serve`.
6. Acesse a aplicação em `http://localhost:4200`.