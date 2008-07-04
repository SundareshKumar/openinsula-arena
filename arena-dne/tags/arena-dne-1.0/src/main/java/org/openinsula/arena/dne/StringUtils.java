/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne;

/**
 * Classe de conveniência utilizada para manipular {@link String}s.
 * 
 * @author yanaga
 * @since 1.0
 */
public class StringUtils {

	/**
	 * Este método verificar se a {@link String} é nula, e se o resultado do seu
	 * <code>trim()</code> não é igual à {@link String} vazia.
	 * @param s A {@link String} a ser verificada.
	 * @return <strong>true</strong> se a {@link String} for vazia,
	 * <strong>false</strong> caso contrário.
	 */
	public static boolean isBlank(String s) {
		if (s == null || s.trim().equals("")) {
			return true;
		}
		return false;
	}

}
