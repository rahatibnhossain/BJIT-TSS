import React, { useContext } from 'react'
import { Box } from "@mui/material";

import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { Home, School, AddBox, Login } from '@mui/icons-material';
import { NavLink, useLocation } from "react-router-dom";


import Link from '@mui/material/Link';
import { Link as ReactRouterLink } from 'react-router-dom';
import { LoginContext } from '../context/LoginContex';
import { Logout } from '../../node_modules/@mui/icons-material/index';


const Sidebar = ({ close }) => {
  const location = useLocation();



  const { userData, role, loggedIn, setLoggedIn, setRole } = useContext(LoginContext);

  

  // const [name, setName] = useState("User");

  // useEffect(() => {
  //   if (userData && userData.data && userData.data.data.role === "APPLICANT") {
  //     setName(userData.data.data.data.firstName);
  //   }
  // }, [userData]);



  const logout = () => {
    window.localStorage.removeItem("tss-token")

    setLoggedIn(false);
    setRole("");

  }


  const isActiveLink = (path) => {
    if (location.pathname === path) {
      return "red";
    }



  };
  return (

    <Box flex={1} sx={{ display: { xs: close ? "none" : "block", sm: "block" } }}>
      <Box position="fixed" >



        <List>

          <ListItem disablePadding>
            <Link flex={1} style={{ textDecoration: 'none', color: isActiveLink("/") }} component={NavLink} to="/" variant="ListItemButton"   >
              <ListItemButton >
                <ListItemIcon>
                  <Home />
                </ListItemIcon>
                <ListItemText primary="Home Page" />
              </ListItemButton>
            </Link>

          </ListItem>

          {role === "APPLICANT" &&
            <ListItem disablePadding>
              <Link flex={1} style={{ textDecoration: 'none', color: isActiveLink("/profile") }} component={NavLink} to="/profile" variant="ListItemButton"   >
                <ListItemButton >
                  <ListItemIcon>
                    <Home />
                  </ListItemIcon>
                  <ListItemText primary="Profile" />
                </ListItemButton>
              </Link>
            </ListItem>

          }


          <ListItem disablePadding>
            <Link flex={1} style={{ textDecoration: 'none', color: isActiveLink("/course") }} component={ReactRouterLink} to="/course" variant="ListItemButton" >
              <ListItemButton >
                <ListItemIcon>
                  <School />
                </ListItemIcon>
                <ListItemText primary="Courses" />
              </ListItemButton>
            </Link>
          </ListItem>


          {!loggedIn ?
            <>

              <ListItem disablePadding>
                <Link flex={1} style={{ textDecoration: 'none', color: isActiveLink("/login") }} component={ReactRouterLink} to="/login" variant="ListItemButton" >
                  <ListItemButton >
                    <ListItemIcon>
                      <Login />
                    </ListItemIcon>
                    <ListItemText primary="Login" />
                  </ListItemButton>
                </Link>
              </ListItem>

              <ListItem disablePadding>
                <Link flex={1} style={{ textDecoration: 'none', color: isActiveLink("/registration") }} component={ReactRouterLink} to="/registration" variant="ListItemButton" >
                  <ListItemButton >
                    <ListItemIcon>
                      <AddBox />
                    </ListItemIcon>
                    <ListItemText primary="Registration" />
                  </ListItemButton>
                </Link>
              </ListItem>
            </>
            :

            <ListItem disablePadding onClick={() => { logout() }}>
              <Link flex={1} style={{ textDecoration: 'none' }} component={ReactRouterLink} to="/" variant="ListItemButton" >
                <ListItemButton >
                  <ListItemIcon>
                    <Logout />
                  </ListItemIcon>
                  <ListItemText primary="Logout" />
                </ListItemButton>
              </Link>
            </ListItem>

          }

          {role === "USER" ?

            <ListItem disablePadding>
              <Link flex={1} style={{ textDecoration: 'none', color: isActiveLink("/registration") }} component={ReactRouterLink} to="/registration" variant="ListItemButton" >
                <ListItemButton >
                  <ListItemIcon>
                    <AddBox />
                  </ListItemIcon>
                  <ListItemText primary="Registration" />
                </ListItemButton>
              </Link>
            </ListItem> : null}



        </List>
      </Box>
    </Box>



  )
}

export default Sidebar