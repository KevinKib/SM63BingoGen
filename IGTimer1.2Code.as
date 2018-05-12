/**
 * ...
 * @author Runouw & Sekanor
 */
class // frame6  
{

	// Variable definitions
	NewgroundsAPI.connectMovie(8160);
	_root.ILTimer = 0;
	_root.ILTimerLastUpdate = 0;
	_root.ILTimerState = "RUN";
	_root.ILTimerAvoidRepeat = 0;
	_root.ILTimerDispMode = "NORMAL";
	_root.ILTimerUpdateMode = "LOADINGZONE";
	_root.ILTimerCurrentMenu = 0;
	_root.ILTimerMenuDelay = 0;
	_root.LatestCode = 0;

}
	
class // DefineSprite824
{
	if((_root._quality == "BEST" || _root._quality == "HIGH") && _root.AutoQuality == true)
	{
	   _root.Qualitynum--;
	}
	qn = _root.Qualitynum;
	qn2 = 0;
	_root._quality = _root.QualityArray[qn];
	time = getTimer();
	count = 0;
	minutes = 0;
	seconds = 0;
	milliseconds = 0;
	frtxt = "running";
	txtMinutes = "";
	txtSeconds = "";
	txtMilliseconds = "";
	i = 1;
	code = 0;
	input = 0;
	
	_root.ILTimerCurrentMenu = 0;
	text_currentTime = "";
	text_1 = "";
	text_2 = "";
	
	KEY_REPEAT_DELAY = 5;
	MENU_OPEN_LENGTH = 150;
	
	enterCode = function()
	{
		// Input
		input = -1;
		if 		(_root.Key1()) input = 1;
		else if (_root.Key2()) input = 2;
		else if (_root.Key3()) input = 3;
		else if (_root.Key4()) input = 4;
		else if (_root.Key5()) input = 5;
		else if (_root.Key6()) input = 6;
		else if (_root.Key7()) input = 7;
		else if (_root.Key8()) input = 8;
		else if (_root.Key9()) input = 9;
		else if (_root.Key0()) input = 0;
		else if (_root.KeyBackspace()) input = 11;
		else if (_root.KeyEnter()) input = 12;
		else if (_root.KeyStar()) input = 13;
		
		// Actions
		if (input == 11)
		{	
			// Backspace: deletion of the last number
			code = Math.floor(code / 10);
		}
		else if ((input >= 0) && (input <= 9))
		{
			// Add the input number
			code = code * 10 + input;
		}
		else if (input == 12)
		{
			codeValidated = true;
		}
		else if (input == 13)
		{
			code = _root.LatestCode;
			codeValidated = true;
		}
		
		if (input != -1) 
		{
			// There is an input: we keep the menu opened a little bit more.
			_root.ILTimerAvoidRepeat = KEY_REPEAT_DELAY;
			_root.ILTimerMenuDelay = MENU_OPEN_LENGTH;
		}
		
		
	}
	
	setStarCoins = function(bool)
	{
		i = 1;
		while(i <= 64)
		{
			_root.StarCoin[i] = bool;
			i++;
		}
		_root.CalculateStarCoins();
	}
	
	setStars = function(bool)
	{
		i = 1;
		while(i <= 64)
		{
			_root.Star[i] = bool;
			i++;
		}
		_root.CalculateStars();
	}
	
	setBowserKeys = function(bool)
	{
		_root.BowserKey1 = bool;
		_root.BowserKey2 = bool;
		_root.BowserKey3 = bool;
	}
	
	setFludd = function(bool)
	{
		_root.SaveFluddH = bool;
		_root.SaveFluddR = bool;
		_root.SaveFluddT = bool;
	}
	
	gotoMenu = function(nbMenu)
	{
		if ( nbMenu == 0)
		{
			_root.ILTimerMenuDelay = 0;
		}
		else
		{
			_root.ILTimerMenuDelay = MENU_OPEN_LENGTH;
		}
		
		_root.ILTimerCurrentMenu = nbMenu;
	}
	
	executeCode = function()
	{
		switch (code)
		{
			case 101:
				// 99 lives
				_root.CharLives = 99;
				break;
			case 102:
				// 0 lives
				_root.CharLives = 0;
				break;
			case 201:
				// Refill FLUDD
				_root.WaterAmount = _root.TotalWater; // cst of value 10000
				break;
			case 301:
				// 100% File
				setStars(true);
				setStarCoins(true);
				setBowserKeys(true);
				break;
			case 311:
				// All Stars
				setStars(true);
				break;
			case 312:
				// All Star Coins
				setStarCoins(true);
				break;
			case 313:
				// All Bowser Keys
				setBowserKeys(true);
				break;
			case 321:
				// No SC & Nozzles
				setFludd(false);
				_root.FluddArray = ["",["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false],["",false,false,false]];
				_root.RestartFludd();
				_root.Fluddpow = "";
				setStarCoins(false);
				break;
			case 322:
				// 0 Stars
				setStars(false);
				break;
			case 323:
				// No Bowser Keys
				setBowserKeys(false);
				break;
			case 324:
				// No Star Coins
				setStarCoins(false);
				break;
			case 401:
				// All Nozzles
				setFludd(true);
				break;
			case 411:
				// Toggle Nozzle H
				_root.SaveFluddH = !_root.SaveFluddH;
				break;
			case 412:
				// Toggle Nozzle R
				_root.SaveFluddR = !_root.SaveFluddH;
				break;
			case 413:
				// Toggle Nozzle T
				_root.SaveFluddT = !_root.SaveFluddH;
				break;
			case 501:
				// Warp to T1
				_root.changecourse("StarIn","8-E1-1",0,0,0,0,100);
				break;
			case 511:
				// Warp to BT3
				_root.changecourse("StarIn","8-1",4128,-10,4128,-10);
				break;
			case 512:
				// Warp to BT3 MH
				_root.changecourse("StarIn","8-2",0,0,0,0);
				break;
			case 513:
				// Warp to BT3 HR1
				_root.changecourse("downtransition2","8-10",500,-1450,500,-1450);
				break;
			case 514:
				// Warp to BT3 HR2
				_root.changecourse("uptransition2","8-10-b",500,-10,500,-10);
				break;
			case 521:
				// Warp to Space R1
				_root.changecourse("StarIn","8-13",0,0,0,0,true);
				break;
			case 522:
				// Warp to Space R2
				_root.changecourse("uptransition2","8-14",0,0,0,0);
				break;
			case 523:
				// Warp to Space R3
				_root.changecourse("uptransition2","8-15",0,0,0,0);
				break;
			case 531:
				// Warp to Escape R1
				_root.changecourse("lefttransition2", "BC-1", 0, 0, 0, 0);
				break;
			case 532:
				// Warp to Escape R2
				_root.changecourse("lefttransition2", "BC-2", 0, 0, 0, 0);
				break;
			case 533:
				// Warp to Escape R3
				_root.changecourse("lefttransition2", "BC-3", 0, 0, 0, 0);
				break;
			case 541:
				// Warp to Basement
				break;
			case 542:
				// Warp to Upstairs
				break;
			case 543:
				// Warp to Star Room
				break;
			case 901:
				// Toggle Characters
				if (_root.CurrentPlayer == "Mario")
				{
					_root.CurrentPlayer = "Luigi";
				}
				else
				{
					_root.CurrentPlayer = "Mario";
				}
				break;
			default:
				// Invalid codes
				break;
		}
	
		// Reset
		_root.LatestCode = code;
		code = 0;
		codeValidated = false;
		gotoMenu(0);
	}
	
	saveState = function()
	{
		_root.SaveArray[0] = _root.Course.Char._x;
		_root.SaveArray[1] = _root.Course.Char._y;
	}
	
	loadState = function()
	{
		_root.Course.Char._x = _root.SaveArray[0];
		_root.Course.Char._y = _root.SaveArray[1];
	}
	
	_root.Timer_calculateSeconds = function()
	{
		minutes = Math.floor(_root.ILTimerLastUpdate / (32 * 60));
		seconds = Math.floor(_root.ILTimerLastUpdate / 32) % 60;
		milliseconds = _root.ILTimerLastUpdate % 32;
		milliseconds = Math.floor(milliseconds * (1000 / 32));
		txtMinutes = minutes;
		txtSeconds = seconds;
		txtMilliseconds = milliseconds;
		if(minutes < 10)
		{
			txtMinutes = "0" + txtMinutes;
		}
		if(seconds < 10)
		{
			txtSeconds = "0" + txtSeconds;
		}
		if(milliseconds < 10)
		{
			txtMilliseconds = "00" + txtMilliseconds;
		}
		else if(milliseconds < 100)
		{
			txtMilliseconds = "0" + txtMilliseconds;
		}
	};
	_root.Timer_updateDisplay = function()
	{
		if (_root.ILTimerState == "RUN" and _root.ILTimerUpdateMode == "ALWAYS")
		{
			_root.ILTimerLastUpdate = _root.ILTimer;
		}
		
		// Update display (frames/seconds)
		if(_root.ILTimerDispMode == "FRAMES")
		{
			_root.TextHint = _root.ILTimerLastUpdate + "\n" + text_1 + "\n" + text_2
		}
		else
		{
			_root.Timer_calculateSeconds();
			_root.TextHint = txtMinutes + ":" + txtSeconds + "." + txtMilliseconds + "\n" + text_1 + "\n" + text_2
		}
	};
	_root.Timer_addTime = function()
	{
	    if(_root.ILTimerState == "RUN")
	    {
			_root.ILTimer = _root.ILTimer + 1;
	    }
	};
	_root.Timer_start = function()
	{
		_root.ILTimer = 0;
		_root.ILTimerLastUpdate = 0;
		_root.ILTimerState = "RUN";
		frtxt = "running";
		_root.Timer_updateDisplay();
	};
	_root.Timer_stop = function()
	{
		_root.Timer_update();
		_root.ILTimerState = "STOP";
		if(_root.ILTimerDispMode == "FRAMES")
		{
			_root.TextHint = _root.ILTimer;
		}
		else
		{
			_root.Timer_calculateSeconds();
			_root.TextHint = txtMinutes + ":" + txtSeconds + "." + txtMilliseconds;
		}
		frtxt = "stopped";
	};
	_root.Timer_toggledisplay = function()
	{
		if(_root.ILTimerDispMode == "NORMAL")
		{
			_root.ILTimerDispMode = "FRAMES";
		}
		else
		{
			_root.ILTimerDispMode = "NORMAL";
		}
		_root.Timer_updateDisplay();
	};
	_root.Timer_input = function()
	{
		
		// Count that prevents the player from pressing inputs twice
		if(_root.ILTimerAvoidRepeat > 0)
		{
			_root.ILTimerAvoidRepeat = _root.ILTimerAvoidRepeat - 1;
		}
		
		// Menu display
		if (_root.ILTimerCurrentMenu == 0)
		{
			text_1 = "";
			text_2 = "";
		}
		else
		{
			text_1 = "Enter your code ! (Press * to use the latest code)";
			text_2 = code;
		}
		
		// Inputs
		if (_root.ILTimerAvoidRepeat == 0)
		{
			if (_root.ILTimerCurrentMenu == 0)
			{
				
				if(_root.KeyPlus())
				{
					if(_root.ILTimerState == "RUN")
					{
						_root.Timer_stop();
					}
					else
					{
						_root.Timer_start();
					}
					_root.ILTimerAvoidRepeat = KEY_REPEAT_DELAY;
				}
				
				if(_root.KeyMinus())
				{
					if(_root.ILTimerUpdateMode == "ALWAYS")
					{
						_root.ILTimerUpdateMode = "LOADINGZONE";
					}
					else
					{
						_root.ILTimerUpdateMode = "ALWAYS";
					}
					_root.ILTimerAvoidRepeat = KEY_REPEAT_DELAY;
				}
				
				if(_root.KeyStar())
				{
					gotoMenu(1);
					_root.ILTimerAvoidRepeat = KEY_REPEAT_DELAY;
				}
			
				if(_root.Key1())
				{
					loadState();
				}
				
				if(_root.Key0())
				{
					saveState();
				}
				
			}
			else if (_root.ILTimerCurrentMenu == 1)
			{
				
				// Variable initialisation
				codeValidated = false;
				
				// Player input
				enterCode();
				
				// Actions
				if (codeValidated) executeCode();
			
				// Exit menu if time goes by
				_root.ILTimerMenuDelay--;
				if (_root.ILTimerMenuDelay == 0) gotoMenu(0);
			}
		}
		
	};
	_root.Timer_updateTime = function()
	{
		_root.ILTimerLastUpdate = _root.ILTimer;
	};
	_root.Timer_update = function()
	{
	    _root.Timer_updateTime();
	    _root.Timer_updateDisplay();
	};
	
	// Code executed on the first frame
	if(_root.ILTimerState == "STOP")
	{
		_root.Timer_start();
	}
	_root.Timer_update();
	stop();
	
	// Code executed on each frame
	onEnterFrame = function()
	{
		_root.Timer_updateDisplay();
	    _root.Timer_input();
	    _root.Timer_addTime();
	};
}

class // DefineSprite1002: ShineSpriteClip
{
	
	stop();
	if(invis == undefined)
	{
	   invis = false;
	}
	if(LevelDesigner == undefined)
	{
	   LevelDesigner = false;
	}
	if(firstrun == undefined)
	{
	   if(LevelDesigner == true)
	   {
		  trace(_root.LDRedCoin);
		  if(_root.LDRedCoin > 0 || _root.LDSilverStar > 0)
		  {
			 invis = true;
		  }
	   }
	   firstrun = true;
	}
	onEnterFrame = function()
	{
	   if(invis == false)
	   {
		  if(_root.Star[starnum] == true)
		  {
			 if(alternateoldframe !== true)
			 {
				gotoAndStop(2);
			 }
			 else
			 {
				_alpha = 50;
				gotoAndStop(1);
			 }
		  }
		  else
		  {
			 gotoAndStop(1);
		  }
		  if(this.box.hitTest(_root.Course.Char._x * _root.coursescale / 100 + _root.Course._x,(_root.Course.Char._y - 20) * _root.coursescale / 100 + _root.Course._y,true))
		  {
			 if(LevelDesigner !== true)
			 {
				if(_root.Star[starnum] == false)
				{
				   _root.Star[starnum] = true;
				   _root.LastItemGot = "gotStar";
				}
				else
				{
				   _root.LastItemGot = "oldStar";
				}
				_root.newstar = true;
				_root.lastPlayinglevel = _root.Playinglevel;
				_root.lastStarnamenum = starnum;
			 }
			 _root.Invincible = false;
			 _root.Metal = false;
			 _root.Invisible = false;
			 _root.PowerTimer = 0;
			 _root.Course.Char.attack = true;
			 _root.Course.Char.attackFrame = "Star";
			 _root.Course.Char.xspeed = 0;
			 _root.Course.Char.yspeed = _root.Course.Char.yspeed / 2;
			 _root.Course.Char._x = _X + _parent._x;
			 _root.Course.Char._y = _Y + _parent._y + 20 * _root.coursescale / 100;
			 if(_root.ILTimerUpdateMode == "ALWAYS")
			 {
				_root.Timer_stop();
			 }
			 else
			 {
				_root.Timer_update();
			 }
			 gotoAndStop("Gone");
		  }
	   }
	   else
	   {
		  gotoAndStop("Invis");
	   }
	};
	if(invis == false)
	{
	   if(_root.Star[starnum] == true)
	   {
		  if(alternateoldframe !== true)
		  {
			 gotoAndStop(2);
		  }
		  else
		  {
			 _alpha = 50;
			 gotoAndStop(1);
		  }
	   }
	   else
	   {
		  gotoAndStop(1);
	   }
	}
	else
	{
	   gotoAndStop(3);
	}


}

class // frame2_2
{
	
	radiansToAngle = function(a)
	{
	   a = 57.29577951308232 * a;
	   return a;
	};
	angleToRadians = function(a)
	{
	   a = a / 180 * 3.141592653589793;
	   return a;
	};
	groundFriction = function(a, b, c)
	{
	   if(a > 0)
	   {
		  d = 1;
	   }
	   else
	   {
		  d = -1;
	   }
	   a = Math.abs(a);
	   a = a - b;
	   if(a < 0)
	   {
		  a = 0;
	   }
	   a = a / c;
	   a = a * d;
	   return a;
	};
	moveObject = function(a, b, c)
	{
	   c._x = c._x + a;
	   c._y = c._y + b;
	};
	moveObjectFromAngle = function(a, b, c)
	{
	   c._x = c._x + Math.cos(angleToRadians(b)) * a;
	   c._y = c._y + Math.sin(angleToRadians(b)) * a;
	};
	moveObjectFromRadians = function(a, b, c)
	{
	   c._x = c._x + Math.cos(b) * a;
	   c._y = c._y + Math.sin(b) * a;
	};
	shuffleArray = function(a, b)
	{
	   d = a.push();
	   i = 0;
	   while(i < b)
	   {
		  e = random(d);
		  f = random(d);
		  if(e !== f)
		  {
			 g = a[e];
			 a[e] = a[f];
			 a[f] = g;
		  }
		  i++;
	   }
	   return a;
	};
	_root.MoveAllPlats = function(Yamount, Xamount)
	{
	   Xamount = Math.round(Xamount * 10) / 10;
	   Yamount = Math.round(Yamount * 10) / 10;
	   if(Math.abs(Yamount) > 0.1 || Math.abs(Xamount) > 0.1)
	   {
		  _root.Course.Platforms._y = _root.Course.Platforms._y + Yamount;
		  _root.Course.Platforms._x = _root.Course.Platforms._x + Xamount;
		  _root.Course.BPlatforms._y = _root.Course.BPlatforms._y + Yamount;
		  _root.Course.BPlatforms._x = _root.Course.BPlatforms._x + Xamount;
		  _root.Course.FrontGFX._y = _root.Course.FrontGFX._y + Yamount;
		  _root.Course.FrontGFX._x = _root.Course.FrontGFX._x + Xamount;
		  _root.Course.BackGFX._y = _root.Course.BackGFX._y + Yamount;
		  _root.Course.BackGFX._x = _root.Course.BackGFX._x + Xamount;
		  _root.Course.Water._y = _root.Course.Water._y + Yamount;
		  _root.Course.Water._x = _root.Course.Water._x + Xamount;
		  _root.Course.Lava._y = _root.Course.Lava._y + Yamount;
		  _root.Course.Lava._x = _root.Course.Lava._x + Xamount;
		  _root.Course.Puddle._y = _root.Course.Puddle._y + Yamount;
		  _root.Course.Puddle._x = _root.Course.Puddle._x + Xamount;
		  _root.Course.Ice._y = _root.Course.Ice._y + Yamount;
		  _root.Course.Ice._x = _root.Course.Ice._x + Xamount;
		  _root.Course.Edge._y = _root.Course.Edge._y + Yamount;
		  _root.Course.Edge._x = _root.Course.Edge._x + Xamount;
		  _root.Course.CamEdge._y = _root.Course.CamEdge._y + Yamount;
		  _root.Course.CamEdge._x = _root.Course.CamEdge._x + Xamount;
		  _root.BGCourse.BG._y = _root.BGCourse.BG._y + Yamount / 10;
		  _root.BGCourse.BG._x = _root.BGCourse.BG._x + Xamount / 10;
		  _root.MidBackground.BG._y = _root.MidBackground.BG._y + Yamount / 5;
		  _root.MidBackground.BG._x = _root.MidBackground.BG._x + Xamount / 5;
		  _root.BGCloseCourse.BG._y = _root.BGCloseCourse.BG._y + Yamount / 2;
		  _root.BGCloseCourse.BG._x = _root.BGCloseCourse.BG._x + Xamount / 2;
		  _root.Course.Char._y = _root.Course.Char._y + Yamount;
		  _root.Course.Char._x = _root.Course.Char._x + Xamount;
		  _root.Course.Enemyhurt._y = _root.Course.Enemyhurt._y + Yamount;
		  _root.Course.Enemyhurt._x = _root.Course.Enemyhurt._x + Xamount;
	   }
	};
	_root.KeyUP = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.mousemoveY + Math.abs(_root.mousemoveX / 2) < -30 && _root.pointer._y < 150 || _root.pointer._y < 75)
		  {
			 return true;
		  }
		  return false;
	   }
	   if(Key.isDown(38))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyDOWN = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.mousemoveY - Math.abs(_root.mousemoveX / 2) > 30 && _root.pointer._y > 150 || _root.pointer._y > 200)
		  {
			 return true;
		  }
		  return false;
	   }
	   if(Key.isDown(40))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyLEFT = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.pointer._x < _root.screensizeX / 2 - _root.screensizeX / 5 || _root.mousemoveX < -15 && _root.pointer._x < _root.screensizeX / 2 - _root.screensizeX / 6 || _root.mousemoveX < -40 && _root.pointer._x < _root.screensizeX / 2 + _root.screensizeX / 8)
		  {
			 return true;
		  }
		  return false;
	   }
	   if(Key.isDown(37))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyRIGHT = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.pointer._x > _root.screensizeX / 2 + _root.screensizeX / 5 || _root.mousemoveX > 15 && _root.pointer._x > _root.screensizeX / 2 + _root.screensizeX / 6 || _root.mousemoveX > 40 && _root.pointer._x > _root.screensizeX / 2 - _root.screensizeX / 8)
		  {
			 return true;
		  }
		  return false;
	   }
	   if(Key.isDown(39))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyTapRIGHT = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.pointer._x > _root.screensizeX / 2 + _root.screensizeX / 8 || _root.mousemoveX > 7 && _root.pointer._x > _root.screensizeX / 2 + _root.screensizeX / 8 || _root.mousemoveX > 30 && _root.pointer._x > _root.screensizeX / 2 - _root.screensizeX / 7)
		  {
			 return true;
		  }
		  return false;
	   }
	};
	_root.KeyTapLEFT = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.pointer._x < _root.screensizeX / 2 - _root.screensizeX / 8 || _root.mousemoveX > 7 && _root.pointer._x < _root.screensizeX / 2 - _root.screensizeX / 8 || _root.mousemoveX > 30 && _root.pointer._x < _root.screensizeX / 2 - _root.screensizeX / 7)
		  {
			 return true;
		  }
		  return false;
	   }
	};
	_root.KeyTapDOWN = function()
	{
	   if(Key.isDown(222))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyTapUP = function()
	{
	   if(Key.isDown(186))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeySPACE = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(_root.MouseDown == true)
		  {
			 return true;
		  }
		  return false;
	   }
	   if(Key.isDown(67) || Key.isDown(32))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyMinus = function()
	{
	   if(Key.isDown(34) || Key.isDown(189))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyPlus = function()
	{
	   if(Key.isDown(33) || Key.isDown(187))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyHome = function()
	{
	   if(_root.WiiMode == true)
	   {
		  if(Key.isDown(80))
		  {
			 return true;
		  }
		  return false;
	   }
	   if(Key.isDown(80))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeySHIFT = function()
	{
	   if(Key.isDown(16))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeySPIN = function()
	{
	   if(Key.isDown(88))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyX = function()
	{
	   if(Key.isDown(88))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyZ = function()
	{
	   if(Key.isDown(90))
	   {
		  return true;
	   }
	   return false;
	};
	
	// More keys
	_root.KeyStar = function()
	{
	   if(Key.isDown(106))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key1 = function()
	{
	   if(Key.isDown(49))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key2 = function()
	{
	   if(Key.isDown(50))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key3 = function()
	{
	   if(Key.isDown(51))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key4 = function()
	{
	   if(Key.isDown(52))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key5 = function()
	{
	   if(Key.isDown(53))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key6 = function()
	{
	   if(Key.isDown(54))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key7 = function()
	{
	   if(Key.isDown(55))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key8 = function()
	{
	   if(Key.isDown(56))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key9 = function()
	{
	   if(Key.isDown(57))
	   {
		  return true;
	   }
	   return false;
	};
	_root.Key0 = function()
	{
	   if(Key.isDown(48))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyBackspace = function()
	{
	   if(Key.isDown(8))
	   {
		  return true;
	   }
	   return false;
	};
	_root.KeyEnter = function()
	{
	   if(Key.isDown(13))
	   {
		  return true;
	   }
	   return false;
	};
	
	_root.ChangeFludd = function()
	{
	   tellTarget(_root.Course.Char)
	   {
		  fluddchanged = false;
		  if(_root.Fluddpow == "")
		  {
			 if(changeFludd == true && (_root.FluddArray[_root.Playinglevel][1] == true || _root.SaveFluddH == true))
			 {
				_root.Fluddpow = "H";
				fluddchanged = true;
			 }
			 else if(changeFludd == true && (_root.FluddArray[_root.Playinglevel][2] == true || _root.SaveFluddR == true))
			 {
				_root.Fluddpow = "R";
				fluddchanged = true;
			 }
			 else if(changeFludd == true && (_root.FluddArray[_root.Playinglevel][3] == true || _root.SaveFluddT == true))
			 {
				_root.Fluddpow = "T";
				fluddchanged = true;
			 }
		  }
		  else if(_root.Fluddpow == "H")
		  {
			 if(changeFludd == true && (_root.FluddArray[_root.Playinglevel][2] == true || _root.SaveFluddR == true))
			 {
				_root.Fluddpow = "R";
				fluddchanged = true;
			 }
			 else if(changeFludd == true && (_root.FluddArray[_root.Playinglevel][3] == true || _root.SaveFluddT == true))
			 {
				_root.Fluddpow = "T";
				fluddchanged = true;
			 }
			 else if(changeFludd == true)
			 {
				_root.Fluddpow = "";
				fluddchanged = true;
			 }
		  }
		  else if(_root.Fluddpow == "R")
		  {
			 if(changeFludd == true && (_root.FluddArray[_root.Playinglevel][3] == true || _root.SaveFluddT == true))
			 {
				_root.Fluddpow = "T";
				fluddchanged = true;
			 }
			 else if(changeFludd == true)
			 {
				_root.Fluddpow = "";
				fluddchanged = true;
			 }
		  }
		  else if(_root.Fluddpow == "T")
		  {
			 if(changeFludd == true)
			 {
				_root.Fluddpow = "";
				fluddchanged = true;
			 }
		  }
		  if(fluddchanged == true)
		  {
			 _root.Mariosound = new Sound(this);
			 _root.Mariosound.attachSound("FluddSwitch");
			 _root.Mariosound.start(0,1);
			 _root.Mariosound.setVolume(_root.MarioVolume);
		  }
	   }
	};

}

class // Quality code
{
	if((_root._quality == "BEST" || _root._quality == "HIGH") && _root.AutoQuality == true)
	{
	   _root.Qualitynum--;
	}
	qn = _root.Qualitynum;
	qn2 = 0;
	_root._quality = _root.QualityArray[qn];
	time = getTimer();
	count = 0;
	frtxt = "fps: unknown";
	stop();
	
	updateQuality = function()
	{
		if(_root.PauseGame == false)
		{
			if(count+1 >= 4)
			{
				if(_root._quality == "LOW")
				{
					_root.Qualitynum = 0;
				}
				else if(_root._quality == "MEDIUM")
				{
					_root.Qualitynum = 1;
				}
				else if(_root._quality == "HIGH")
				{
					_root.Qualitynum = 2;
				}
				else if(_root._quality == "BEST")
				{
					_root.Qualitynum = 3;
				}
				qn = _root.Qualitynum;
				diff = Math.round(10000 / (getTimer() - time) * count) / 10;
				time = getTimer();
				count = 0;
				if(diff / 32 < 0.7)
				{
					qn2--;
				}
				else if(diff / 32 > 0.9)
				{
					qn2++;
				}
				if(qn2 > 30)
				{
					qn2 = 0;
					qn = Math.min(qn + 1,3);
				}
				else if(qn2 < -15)
				{
					qn2 = 0;
					qn = Math.max(qn - 1,0);
				}
				if(_root.AutoQuality == true)
				{
					if(_root._quality !== _root.QualityArray[qn])
					{
						_root._quality = _root.QualityArray[qn];
					}
					_root.Qualitynum = qn;
				}
			}
		}
		else if(count+1 >= 4)
		{
			diff = Math.round(10000 / (getTimer() - time) * count) / 10;
			time = getTimer();
			count = 0;
		}
	}
	
	onEnterFrame = function()
	{
		updateQuality();
	};
}
