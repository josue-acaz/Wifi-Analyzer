# Wifi-Analyzer

Programa construído para a disciplina de Rede de Computadores II, com o objetivo de auxiliar alunos e professores na obtenção de dados de rede wifi da Universidade Federal do Oeste do Pará.
São realizados os seguintes testes no programa:

1. Quantidade de usuários na rede
2. Potencia de Sinal
3. Largura de banda
4. Perda de pacotes
5. Atraso da rede
6. Jitter

Além disso, após os testes, todos os arquivos do cliente e cliente reverso (no caso do UDP) são formatados e colocados em extensão '.csv' para que possam ser utilizados em Excel ou Calc. O programa também possui um complemento próprio que trabalha com a plotagem de gráficos, como gráficos de dispersão em x e y, bem como mapa de calor quando selecionamos pontos para formar uma matriz no local que pretendemos analisar.

Para executar os testes no programa é necessário os seguintes componentes:

1. SO Ubuntu
2. Network Mapper (Nmap).
2. Iperf3 como cliente rodando no hospedeiro.
4. Iperf3 como servidor rodando em outra máquina.
