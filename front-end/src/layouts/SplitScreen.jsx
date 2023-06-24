import React from 'react'
import styled from 'styled-components'
import "./splitscreen.css"


const Pane = styled.div`
  flex:${props => props.weight};
`;

const SplitScreen = ({
  children,
  leftWeight,
  rightWeight

}) => {


  const [left, right] = children
  return (
    <div className='splitscreen-container'>

      <Pane weight={leftWeight}>
        {left}
      </Pane>
      <Pane weight={rightWeight}>
        {right}
      </Pane>
    </div>

  )
}

export default SplitScreen