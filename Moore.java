/**
 * 
 * Copyright (C) 2017 Emmanuel DESMONTILS
 * 
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 * 
 * 
 * 
 * E-mail:
 * Emmanuel.Desmontils@univ-nantes.fr
 * 
 * 
 **/


/**
 * AFD.java
 *
 *
 * Created: 2017-08-25
 *
 * @author Emmanuel Desmontils
 * @version 1.0
 */

import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;


class EtatMoore extends Etat {
	protected String out ;

	public EtatMoore(String n, String out){
		super(n);
		this.out = out ;
	}
}

public class Moore extends Transducteur {

	public Moore(Set<String> A, Set<Etat> Q, String i, Set<String> F, Set<Transition> mu) throws JFSMException {
		super(A,Q,i,F,mu);
		Iterator<Etat> it = Q.iterator();
		while(it.hasNext()){
			Etat t = it.next();
			assert t instanceof EtatMoore : "Un état n'est pas un état de Moore";
		}
	}

	public boolean next(String symbol) {
		boolean ok = super.next(symbol);
		if (ok) {
			Transition t = histo.peek();
			if (t.cible != trash.name) {
				TransitionMealy tm = (TransitionMealy)t;
				res.add(tm.prod);
			}  
			return true;
		} else return false;
	}
}