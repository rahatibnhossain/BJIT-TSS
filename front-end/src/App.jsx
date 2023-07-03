import Header from "./components/Headers/Header"
import HeroSection from "./components/HeroSection/HeroSection"
import Home from "./pages/Home/Home"


function App() {

  return (
    <>

        <Header logedIn={false} admin={true} />
        <HeroSection>
          <Home />
        </HeroSection>
        <div><h1>Footer</h1></div>





    </>
  )
}

export default App
