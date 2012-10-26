History Token Wrapper
=============

É uma API simples para lidar com hashes (tudo após o # na URL), chamado de History Token no GWT.

Esta API assume somente a parte de parsing do hash, sendo este fornecido já sem a cerquilha (#).
O método de obtenção do hash não é parte do escopo desta API.

Foi desenvolvida inicialmente para ser usada com GWT (Google Web Toolkit).
Todo o código pode ser usado no client-side da aplicação GWT.

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
        doOtherCommand(); // outro comando
    }