package com.github.rodolfo42.historytoken;
/**
 * Wrapper para um history token (window.hash). Este objeto é imutável<br>
 * <br>
 * Anatomia de um token:<br>
 * <br>
 * <code>#/&lt;command&gt;/&lt;param1&gt;/&lt;param1&gt;/&lt;paramN&gt;</code><br>
 * <br>
 * 
 * Barras ("/") servem para separar o comando de seus parâmetros, e esses entre
 * si.<br>
 * Barras no começo ou no final do token são descartadas ("/command/" se torna
 * apenas "command"), porém o token continua inalterado internamente, e pode ser
 * recuperado através do método {@link #getToken}
 */
public class HistoryToken {

	/**
	 * Token original que este HistoryToken contém, sem modificações
	 */
	private final String token;

	/**
	 * Partes do token
	 */
	private String[] parts;

	public HistoryToken(String token) {
		if (token == null) {
			token = "";
		}
		this.token = token;
		this.parts = breakUpToken();
	}

	/**
	 * @return indica se este token está vazio, ou seja: ""
	 */
	public boolean isEmpty() {
		return (parts == null || parts.length == 0);
	}

	/**
	 * Indica se este token corresponde ao comando <code>command</code>
	 * fornecido. Case-sensitive.
	 * 
	 * @param command
	 * @return true se o comando é correspondente; false ao contrário
	 */
	public boolean isCommand(String command) {
		return command.equals(getCommand());
	}

	/**
	 * Indica se o token é somente um comando, sem parâmetros.
	 * 
	 * @return true somente se existir um comando, e não existirem parâmetros;
	 *         false ao contrário
	 */
	public boolean isCommandOnly() {
		return (hasCommand() && !hasParams());
	}

	/**
	 * Indica se o token corresponde á um comando com um ou mais parâmetros
	 * 
	 * @return true somente se existe um comando e pelo menos um parâmetro;
	 *         false ao contrário
	 */
	public boolean isCommandWithParams() {
		return (getCommand() != null) && (hasParams());
	}

	/**
	 * Retorna o comando representado por este token
	 * 
	 * @return somente comando, sem barras
	 */
	public String getCommand() {
		return (parts == null || parts.length == 0) ? null : parts[0];
	}

	/**
	 * Retorna o token original fornecido, sem modificações.
	 * 
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Retorna os parâmetros deste token, se houverem
	 * 
	 * @return um array com os parâmetros; null se não existirem parâmetros
	 */
	public String[] getParams() {
		if (parts == null || parts.length < 2) {
			return null;
		}
		int size = parts.length - 1;
		String[] params = new String[size];
		for (int i = 1; i < parts.length; i++) {
			params[i - 1] = new String(parts[i]);
		}
		return params;
	}
	
	
	/**
	 * Retorna o parâmetro que está no índice indicado por <code>index</code>
	 * 
	 * @param index
	 * @return o parametro, ou null caso nao exista
	 */
	public String getParam(int index) {
		if(hasParams()) {
			String[] params = getParams();
			if(index <= params.length -1) {
				return params[index];
			}
		}
		return null;
	}

	/**
	 * Indica se há parâmetros disponíveis neste token
	 * 
	 * @return true se houver; false ao contrário
	 */
	public boolean hasParams() {
		return (getParams() != null);
	}

	/**
	 * Indica se existe um comando, independemente de existir parâmetros ou não
	 * 
	 * @return true se existe; false ao contrário
	 */
	public boolean hasCommand() {
		return (parts != null && parts.length > 0);
	}

	/**
	 * Método privado que quebra/processa o token informado em partes (comando e
	 * parametros)
	 * 
	 * @return retorna as partes do token; null se não forem achadas partes ou o
	 *         token estiver vazio
	 */
	private String[] breakUpToken() {
		String tkn = token;
		if (tkn == null || tkn.isEmpty()) {
			return null;
		}

		/**
		 * nesta parte, é utilizado new String(string), pois apesar do método
		 * String.substring() retornar também uma nova String, a mesma ainda
		 * carrega a String original, e usa de um parâmetro interno chamado
		 * "offset" para controlar qual parte da string é relevante
		 */

		// retira as barras do começo do token
		while (!tkn.isEmpty() && tkn.indexOf('/') == 0) {
			tkn = new String(tkn.substring(1, tkn.length()));
		}
		// retira as barras do final do token
		while (!tkn.isEmpty() && tkn.indexOf('/') == tkn.length() - 1) {
			tkn = new String(tkn.substring(0, tkn.length() - 1));
		}

		if (tkn.contains("/")) {
			String[] parts = tkn.split("/");
			return parts;
		} else if (tkn.isEmpty()) {
			return null;
		} else {
			return new String[] { tkn };
		}
	}
}