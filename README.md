### Göran

A tank coded in Java for the RoboCode game https://robocode.sourceforge.io/.

It is automated and works as intended. It finds a target and keeps focus on it. Depending on the distance to target it uses different ammo strengths. The tank has a limited amount of energy that is used when shooting, so the calculations makes the tank use less energy in distant shots than close shots. The body and turret is separated in movement so the tank can move where ever but still keep the turret targeting the opponent. It also handles the arena size so it does not collide with walls, and it calculates the distance to opponent and stears away if it is about to collide. Collisions cause damage to tank so it is necessary to keep it from colliding. This tank was created as a project in my Java education and won the finals in an arena with a total of 10 tanks.
