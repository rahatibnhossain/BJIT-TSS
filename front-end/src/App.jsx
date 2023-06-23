import SplitScreen from "./layouts/SplitScreen"

const LeftHandComponent = ({name}) => {
  return (
    <h2 style={{ backgroundColor: "red" }} >Hello d {name}</h2>
  )
}

const RightHandComponent = ({name}) => {
  return (
    <p style={{ backgroundColor: "blue" }}  >Hello {name}</p>
  )
}

function App() {

  return (
    <>
      <SplitScreen leftWeight={1} rightWeight={2}   >
        <LeftHandComponent name="Rahat"/>
        <RightHandComponent name="Rahat"/>


      </SplitScreen>
    </>
  )
}

export default App
