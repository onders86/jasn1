/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jasn1.compiler.model;

import java.util.ArrayList;
import java.util.List;

public class SymbolsFromModule {
    public AsnOidComponentList cmplist;
    public AsnDefinedValue defval;
    public boolean isDefinedValue;
    public boolean isOidValue;
    public String modref;
    public List<String> symbolList = new ArrayList<>();

}
