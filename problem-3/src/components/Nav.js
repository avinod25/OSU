import React from 'react';

const Nav = () => {  
  return (
    <div className="stats" role="menubar">
      <span ><img className="logo" src="vinod-logo-sq.png" alt="OSU Problem 3" /></span>
      <span className="global-nav-view">
      <a href='/galleries' >Galleries</a>
      </span>
      <span className="stats-separator">&nbsp;</span>
      <span className="global-nav-view">
      <a href='/favorites'>Favorites</a>
      </span>            
    </div>
    
  );
}

export default Nav;
