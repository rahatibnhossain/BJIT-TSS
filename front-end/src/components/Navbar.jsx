import React, { useContext, useEffect, useState } from 'react'
import { theme } from "../theme/theme";
import { Menu, Tooltip, MenuItem, Badge, Box, InputBase, Container, Stack, AppBar, Toolbar, Typography, styled } from "@mui/material";
import { Mail, Notifications } from "@mui/icons-material";
import { Avatar } from '../../node_modules/@mui/material/index';
import { LoginContext } from '../context/LoginContex';
import { useNavigate } from '../../node_modules/react-router-dom/dist/index';
const StyledToolbar = styled(Toolbar)({
  display: "flex",
  justifyContent: "space-between"
})

const Search = styled("div")(({ theme }) => ({
  backgroundColor: "white",
  padding: "0 10px",
  borderRadius: theme.shape.borderRadius,
  width: "40%"

}))
const Icons = styled(Box)(({ theme }) => ({
  display: "none",
  gap: "20px",
  alignItems: "center",
  [theme.breakpoints.up("sm")]: {
    display: "flex"
  }

}))

const UserBox = styled(Box)(({ theme }) => ({
  display: "flex",
  gap: "10px",
  alignItems: "center",
  [theme.breakpoints.up("sm")]: {
    display: "none"
  }
}))

const Navbar = ({ courseNumber, onClose }) => {

  const navigate = useNavigate();

  const { loggedIn, setLoggedIn, userData, role, appliedCoursesGlobal, setappliedCoursesGlobal } = useContext(LoginContext);

  const logout = () => {
    window.localStorage.removeItem("tss-token")

    setLoggedIn(false);

  }

  const [open, setOpen] = useState(false);



  const [name, setName] = useState("User");

  useEffect(() => {
    if (userData && role === "APPLICANT") {
      setName(userData.firstName)
    }
  }, [userData]);





  return (
    <AppBar position="sticky" color="primary">
      <StyledToolbar>
        <Typography variant="h6" sx={{ display: { xs: "none", sm: "block" } }}>Trainee Selection System</Typography>

        <Typography onClick={() => onClose()} variant="h6" sx={{ display: { xs: "block", sm: "none" } }} >TSS</Typography>
        <Search><InputBase placeholder='Search..' /></Search>
        <Icons>

          <Tooltip title={`You have applied ${appliedCoursesGlobal} courses`}>

            <Badge badgeContent={appliedCoursesGlobal} color="error">
              <Mail />
            </Badge>
          </Tooltip>

          <Tooltip title={`There are ${courseNumber ? courseNumber : 0} courses available`}>

            <Badge badgeContent={courseNumber ? courseNumber : 0} color="error">
              <Notifications />
            </Badge>
          </Tooltip>

          <Avatar
            sx={{ width: 30, height: 30 }}
            src="https://www.w3schools.com/w3images/avatar2.png"
            onClick={e => setOpen(true)}
          />

        </Icons>
        <UserBox onClick={e => { navigate("/profile") }}>
          <Avatar sx={{ width: 30, height: 30 }} src="https://www.w3schools.com/w3images/avatar2.png" />
          <Typography variant="span">

            {name}

          </Typography>
        </UserBox>
      </StyledToolbar>

      <Menu
        id="demo-positioned-menu"
        aria-labelledby="demo-positioned-button"
        open={false}
        onClose={e => { setOpen(false) }}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}

      >

      </Menu>
    </AppBar>


  )
}

export default Navbar