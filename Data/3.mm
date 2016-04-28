%%MatrixMarket DSM 1 Coordinate General 0 Pattern IR
%%MatrixMarket DSM 1 Coordinate Symmetric 0 Pattern IR
%%MatrixMarket DMM 1 Coordinate General 0 Pattern
%
% MDM model of BMW’s Hybrid Vehicle Architecture Concepts
% Example 8.1 ; Steven D Eppinger and Tyson R Browning;
% Design structure matrix methods and applications; MIT press, 2012]
% Number of domain: 2
%
% This model is a MDM (Multi-Domain Matrix) consisting of two DSM domains and
% One DMM (Domain Mapping Matrix) relating two DSMs.
% DMM maps the domain of one DSM to the domain of another DSM.
% The First DSM is a 19X19 matrix; The Second DSM is a 27X27 matrix;
% The resulting DMM is a 19X27 matrix in block row-major order.
%
% A = [ A11 A12;0 A22]
% A11: DSM 1
% A22: DSM 2
% A12: DMM 1
%
% Domain 1 (DSM 1):
% Number of attributes: 0
% Input convention: Input in row (IR)
%beginDomain
% Product Functions DSM
%endDomain
%
%beginModElement
%
% 1 = Store Fuel, 2 = Store Electric Energy, 3 = Convert Fuel into Mechanical Energy,
% 4 = Convert Mechanical into Energy, 5 = Convert Electrical into Mechanical Energy,
% 6 = Deliver (Recover) torque to (from) wheels, 7 = Convert Moment transferred,
% 8 = Equate Rotation, 10 = Couple/Uncouple Moment, 11 = Release Energy as Heat to the Environment,
% 12 = Transfer Heat (to cooling system), 13 = Transfer Moment to (from) the Road,
% 14 = Slow or Stop Vehicle(recovering energy), 15 = Slow or Stop Vehicle (using friction),
% 16 = Control Energy Flow, 18 = Consume EI. Energy for Auto Accessory OPS,
% 19 = Consume Mech. Energy for Engine Accessory
%
%endModElement
%
%beginAttribute
%
%endAttribute
% Domain 2 (DSM 2):
% Number of Attribute: 1
% Input convention: Input in row (IR)
%beginDomain
% Components DSM
%endDomain
%
%beginModElement
% 1 = Fuel Tank, 2 = High Voltage Battery, 4 = Internal Combustion Engine,
% 5 = E-Motor/Generator1, 11 = Transmission, 13 = Differential Gear,
% 18 = Clutch Direct Coupling1, 21 = Cooling System, 22 = Wheels,
% 23 = Brake-system, 24 = Power Electronics/Inverter,
% 26 = Additional Electric Accessories, 27 = Mechanical Accessories
%endModElement
%beginAttribute
%
%endAttribute
%beginDomain
% Product Functions-Components DMM
%endDomain
%beginModElement
% DMM 1 represents mapping between DSM 1 and DSM 2
% DMM 1 has 19 Rows and 27 Columns.
%endModElement
%
%beginAttribute
%
%endAttribute
19 19 42
1 3
2 12
27 27
1 4
2 21 15
19 27
1 1
2 2
