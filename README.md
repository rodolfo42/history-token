History Token Wrapper
=============

### Portuguese ###

É uma API simples para lidar com hashes (tudo após o # na URL), chamado de "token" no GWT.

Esta API vai somente até o *parsing* do hash, sendo este fornecido já sem o resto da URL.
O método de obtenção do hash não é parte do escopo desta API.

Foi desenvolvida inicialmente para ser usada com GWT (Google Web Toolkit).
Todo o código pode ser usado no client-side (e server-side) da aplicação GWT.

Características:
+ fornece um objeto imutável: <code>HistoryToken</code>
+ abstração de comandos e parâmetros
+ lida com barras antes ou depois do hash
+ <code>HistoryToken</code> usa somente tipos primitivos ou da API java padrão (java.lang)

Exemplo:
    
    HistoryToken token = new HistoryToken(History.getToken());

    if(token.isCommand("view")) {
        if(token.hasParams()) {
            // existem parâmetros
            // Ex: http://www.exemplo.com/index#view/345
            doViewWithParams(token.getParams());
        } else {
            doViewWithoutParams(); // não existem parâmetros (somente #view)
        }
    } else {
        doOtherCommand();
    }


### English ###

A simple API designed to ease things when dealing with URL hashes (called "token" in GWT).

This API only goes so far as to *parse* the token, which is already provided without the rest of the URL.
How the application actually gets to the URL hash remains outside this API's scope.

It was originally developed to be used in GWT apps, as it's code is 100% translatable and can be used in the client-side of GWT.

Características:
+ provides an immutable object: <code>HistoryToken</code>
+ command & parameters abstraction
+ deals with leading and trailing slashes
+ <code>HistoryToken</code> only primitives or java.lang API

Usage:
    
    HistoryToken token = new HistoryToken(History.getToken());

    if(token.isCommand("view")) {
        if(token.hasParams()) {
            // params detected
            // Ex: http://www.example.com/index#view/345
            doViewWithParams(token.getParams());
        } else {
            doViewWithoutParams(); // no params (only #view)
        }
    } else {
        doOtherCommand();
    }
