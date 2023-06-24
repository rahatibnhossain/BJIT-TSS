import React from 'react'
import { FaBars } from "react-icons/fa"
import "./header.css"

const Header = ({ logedIn = false, admin = false }) => {
  return (


    <nav className='main-nav'>
      <div className="logo">
        <h2>
          <span>T</span>RAINEE <span>S</span>ELECTION</h2>
      </div>
      <div className="menu-link">
        <ul>
          <li><a href="#">Home</a></li>
          {admin &&
            <>
              <li><a href="#">Admin DashBoard</a></li>

            </>
          }

          <li><a href="">All Circulars</a></li>
          <li><a href="">Contact</a></li>

          {
            logedIn ?
              <>
                <li><a href="">Profile</a></li>
              </>
              :
              <>
                <li><a href="">Registration</a></li>
                <li><a href="">Login</a></li>

              </>

          }


        </ul>
      </div>





    </nav>


  )
}

export default Header
