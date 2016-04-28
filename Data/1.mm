%%MatrixMarket DSM 1 Array General 4 Real Real Real Integer IC
%
% Product Architecture DSM Model of AW101 Change Propagation
% Example Fig. 3.6.3;[Steven D Eppinger and Tyson R Browning;
% Design structure matrix methods and applications; MIT press, 2012].
% Number of domains: : 1
% Number of attributes : 4
% Input convention: : Input in column (IC)
%
%beginDomain
% Product Architecture DSM
%endDomain
%
%beginModElement
% 1 = air conditioning, 2 = auxillary electronics, 3 = avionics,
% 4 = bare fuselage, 5 = cabling and piping , 6 = engines,
% 7 = engine auxillaries, 8 = equipment and furnishings,
% 9 = fire protection, 10 = flight control systems, 11 = fuel,
% 12 = fuselage additional items, 13 = hydraulics,
% 14 = ice and rain protection, 15 = main rotor blades, 16 = main rotor head,
% 17 = tail rotor, 18 = transmission, 19 = weapons and defensive systems
%endModElement
%
%beginAttribute
% 1. Impact(height), Real; 2. Likelihood(width), Real; 3. Risk(height*width), Real;
% 4. Change Propagation(shade), Integer (Red = 3, Amber = 2, Green = 1)
%endAttribute
19 -19
0 0 0 0
0.4 0.8e-2 0.32
0.7 0.8 0.56
0.4 0.9 0.36
0.3 0.9 0.27
0.2 0.1 0.02
0.8 0.1 0.08
0.2 0.9 0.18
0.1 0.8 0.08
0.6 0.7 0.42
2
3
2
2
1
2
2
1
2
