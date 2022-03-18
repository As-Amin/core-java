package com.corejava.packages.themes;

import com.formdev.flatlaf.FlatDarculaLaf;

public class CoreJava
	extends FlatDarculaLaf
{
	public static final String NAME = "CoreJava";

	public static boolean setup() {
		return setup( new CoreJava() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, CoreJava.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
