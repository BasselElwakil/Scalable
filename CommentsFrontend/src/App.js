import React from 'react'
// import { render } from 'react-dom'
import { FaRegThumbsUp,FaThumbsUp,FaCircleNotch } from 'react-icons/fa';
import axios from 'axios';
import {Dots} from 'react-preloaders';

export default class App extends React.Component {
  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      value: '333',
      comments:[],
      likes:[],
      newComment:'',
      load:false,
      loading:[],
      loadComment:false,
      count:0
    };
  }
  componentDidMount() {
    
  }
  handleSubmit(event) {
    event.preventDefault();
    this.setState({load:true},this.getComments);
   

  }
   makeid(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
       result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
 }
  addComment = () =>{
    // alert(this.state.newComment)  
    this.setState({loadComment:true})

    var data={"Type": "insert","PostId": this.state.value,"Comment": this.state.newComment,"User": this.makeid(5),"likes":0}

    axios.post('http://localhost:8080/',data ).then(res => {
      if(res.status===200){
        var xy=[...this.state.comments]
        xy.push(data)
        this.setState({comments:xy,newComment:''})
      }
        console.log(res);
        console.log(res.data);
        this.setState({loadComment:false})
      }) .catch(function (error) {
        // dispatch({type: USER_EXISTS}
        console.log(error.response)
        this.setState({loadComment:false})
      });
  }

  getComments = () => {
    console.log(this.state.load)
    var data={"Type": "getCom","PostId": this.state.value}

    axios.post('http://localhost:8080/',data ).then(res => {
      if(res.status===200){
        this.setState({comments:res.data,count:1})
        var arr=[];
        var arr2=[];
        for(var i=0;i<res.data.length;i++){
      arr[i]=false;
      arr2[i]=false;
        }
    this.setState({likes:arr,loading:arr2})
      }else{
        this.setState({count:0})
      }
        console.log(res);
        console.log(res.data);
        this.setState({load:false})
      }) .catch(function (error) {
        // dispatch({type: USER_EXISTS}
        console.log(error)
        console.log(error.response)
        // this.setState({load:false,count:0})
        alert("Network Error.")
      });
  };

  addLike(index,like){
    var data
    var xx=[...this.state.loading]
    xx[index]=true;
    this.setState({loading:xx})
    if(like){
      data={"Type": "like","PostId": this.state.value,"User":this.state.comments[index].User}
    axios.post('http://localhost:8080/',data ).then(res => {
      // this.setState({comments:res.data})
      if(res.status===200){
        var x=this.state.likes;
        x[index]=!this.state.likes[index];
        var c=this.state.comments;
        if(like){
        c[index].likes=c[index].likes+1;
      }else{
        c[index].likes=c[index].likes-1;
      }
      this.setState({likes:x,comments:c})
      }
    xx=[...this.state.loading]
    xx[index]=false;
    this.setState({loading:xx})
      console.log(res);
      console.log(res.data);
    }) .catch(function (error) {
      xx=[...this.state.loading]
      xx[index]=false;
      this.setState({loading:xx})
      console.log(error.response)
    });

  }else{
    data={"Type": "unlike","PostId": this.state.value,"User":this.state.comments[index].User}
    axios.post('http://localhost:8080/',data ).then(res => {
      if(res.status===200){
        var x=this.state.likes;
        x[index]=!this.state.likes[index];
        var c=this.state.comments;
        if(like){
        c[index].likes=c[index].likes+1;
      }else{
        c[index].likes=c[index].likes-1;
      }
      this.setState({likes:x,comments:c})

      }
      xx=[...this.state.loading]
      xx[index]=false;
      this.setState({loading:xx})
      console.log(res);
      console.log(res.data);
    }) .catch(function (error) {
      // dispatch({type: USER_EXISTS}
      console.log(error.response)
      xx=[...this.state.loading]
      xx[index]=false;
      this.setState({loading:xx})
    });
       }
  }


 


  render() {
    return (
      <div style={{display:'flex',flexDirection:'column',flex:1,alignItems:'center',}}>
      <form style={{marginTop:'20px'}} onSubmit={this.handleSubmit}>
        <label htmlFor="username">Post ID</label>
        <input id="username" name="username" type="text" onChange={(e)=>this.setState({value:e.target.value})}  value={this.state.value} />

        {/* <label htmlFor="email">Enter your email</label>
        <input id="email" name="email" type="email" />

        <label htmlFor="birthdate">Enter your birth date</label>
        <input id="birthdate" name="birthdate" type="text" /> */}

        <button>Show Comments</button>
      </form>
       {this.state.load? 
              <Dots customLoading={true} />
       :null} 
       <div>
  <h1>{this.state.load}</h1></div>
      {this.state.comments.map((item, key) =>
      <div key={key} style={styles.comment}>
    <img style={{width:'30px',height:'30px',borderRadius:'15px',marginLeft:'30px',}}
     src="https://placeimg.com/140/140/any" alt="Italian Trulli"/>
     <div style={styles.subContainer}>
       <div style={styles.commentContainer}>
     <p style={{fontWeight:'bold',marginTop:'10px',marginLeft:'10px'}}>{item.User}</p>
     <p style={{marginTop:0,marginLeft:'10px'}}>{item.Comment}</p>
</div>

     <div style={styles.likeContainer}>
       {this.state.loading[key]?
                   <FaCircleNotch/>
       :
       this.state.likes[key]?
            <div onClick={()=>this.addLike(key,false)}>
            <FaThumbsUp/>
            </div>

       :
       <div onClick={()=>this.addLike(key,true)}>
            <FaRegThumbsUp/>
            </div>

      }
    
     <p style={{marginTop:0}}>{item.likes} likes</p>
     </div>
     </div>
    </div>

      )

      }
            {/* <form style={{marginTop:'20px'}} onSubmit={this.addComment}> */}
          {this.state.count===0?null:
          <div>
          <input style={styles.newComment} id="comment" name="comment" type="text" onChange={(e)=>this.setState({newComment:e.target.value})}   value={this.state.newComment} />
        
          <button style={{marginLeft:'10px'}} onClick={this.state.loadComment?null:this.addComment}>{this.state.loadComment?'Loading':'Add Comment'}</button>
          </div>
        }
          {/* </form> */}

{/* <button>Show Comments</button> */}
      </div>
    );
  }
}
const styles = {
  comment :{
    display: 'flex',
    flexDirection:'row',
    marginTop:'10px',
    // alignItems:'center'
  },
  subContainer:{
    backgroundColor:'#f1f2f5',
    marginLeft:'10px',
    borderRadius:'20px',
    width:'550px',
    display: 'flex',
    flexDirection:'row',
    justifyContent: 'flex-start',
  },
  commentContainer:{
    flex:6,
  },
  likeContainer:{
    // marginLeft:'auto',
    marginRight:'5px',
    display:'flex',
    flexDirection:'column',
    justifyContent:'center',
    alignItems:'center',
    height:'100%',
    flex:1
  },
  newComment:{
    width:'450px',
    marginLeft:'70px',
    marginTop:'10px',
    height:'50px',
    backgroundColor:'#f1f2f5',
    borderRadius:'20px'
  }
}