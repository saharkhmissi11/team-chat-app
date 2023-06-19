export interface Message{
    id?:number;
    senderId:string;
    receiverId:string;
    content:string;
    date:String;
    status:string;
    sentAt:Date;
    senderName:string;
}