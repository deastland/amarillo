package com.sfb;

/**
 * Values that will not change throughout the execution of the program.
 * Later, some may be moved to a config file.
 * 
 * @author deastland
 *
 */
public class Constants {

	public static final double TRANS_ENERGY			= 0.2;							// The energy required to activate a single transporter box.
	public static final int    IMPULSES_PER_TURN	= 32;							// The number of impulses in every turn.
	public static final int    WEAPON_FIRE_DELAY	= (int)(IMPULSES_PER_TURN / 4);	// Weapons must wait 1/4 turn before firing again.
	
	// For each impluse, an array of the speeds that get a move.
	public int[][] IMPULSE_CHART = {
/*Impulse*/
/*1*/			{32},
/*2*/			{32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16},
/*3*/			{32,31,30,29,28,27,26,25,24,23,22,15,14,13,12,11},
/*4*/			{32,31,30,29,28,27,26,25,24,21,20,19,18,17,16,10,9,8},
/*5*/			{32,31,30,29,28,27,26,23,22,21,20,15,14,13,7},
/*6*/			{32,31,30,29,28,27,25,24,23,22,19,18,17,16,12,11,6},
/*7*/			{},
/*8*/			{},
/*9*/			{},
/*10*/			{},
/*11*/			{},
/*12*/			{},
/*13*/			{},
/*14*/			{},
/*15*/			{},
/*16*/			{},
/*17*/			{},
/*18*/			{},
/*19*/			{},
/*20*/			{},
/*21*/			{},
/*22*/			{},
/*23*/			{},
/*24*/			{},
/*25*/			{},
/*26*/			{},
/*27*/			{},
/*28*/			{},
/*29*/			{},
/*30*/			{},
/*31*/			{},
/*32*/			{32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1},
	};
}
