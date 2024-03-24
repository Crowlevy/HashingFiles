# CRIPTOGRAFIA DE ARQUIVOS EM JAVA UTILIZANDO AES

Este é um projeto Java simples que permite criptografar e descriptografar arquivos usando o algoritmo AES com o modo CBC (Cipher Block Chaining) e o padding PKCS5.

## **PASSO 1**: SELEÇÃO OBJETIFICADA

Ao executar o programa, você é apresentado com duas opções:

1. **Criptografar Arquivo**: Permite que você selecione um arquivo para criptografar.
2. **Descriptografar Arquivo**: Permite que você selecione um arquivo criptografado para descriptografar.


## **PASSO** 2: CRIPTOGRAFAR ARQUIVO

Ao selecionar a opção "Criptografar Arquivo", uma janela de seleção de arquivo é aberta. Você pode escolher o arquivo que deseja criptografar.


Em seguida, você será solicitado a inserir a senha de criptografia. Isso é usado para derivar a chave AES.
O arquivo será então criptografado usando a senha fornecida e uma chave derivada(AES).

## **Passo 3**: DESCRIPTOGRAFAR ARQUIVO

A lógica de descriptografar é bem semelhante a anterior, somente é necessário de uma chave de validação para restaurar o arquivo e caso a senha seja incorreta o programa encerra. Para iniciar selecione a opção "Descriptografar Arquivo", uma janela de seleção de arquivo é aberta. Você pode escolher o arquivo criptografado que deseja descriptografar.


Em seguida, você será solicitado a inserir a senha do arquivo. Esta é a senha usada para criptografar o arquivo original.
O arquivo será então descriptografado usando a senha fornecida e a chave derivada.

## Como Funciona

O programa utiliza o algoritmo AES (Advanced Encryption Standard) para criptografar e descriptografar arquivos. A senha fornecida é usada para derivar uma chave AES utilizando PBKDF2 com um salt e um número fixo de iterações. O modo de operação CBC é utilizado para garantir que cada bloco de texto cifrado dependa dos blocos anteriores, aumentando a segurança.

## CASO HAJA ALGUM BUG POR FAVOR REPORTAR OU IMPLEMENTAR UM FORK:)
<img src="muitoEu.jpg" width = 30%>