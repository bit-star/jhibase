import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

export interface IUucUserBaseinfoMp {
  id?: number;
  msgReceiverGroup?: IMsgReceiverGroupMp;
}

export class UucUserBaseinfoMp implements IUucUserBaseinfoMp {
  constructor(public id?: number, public msgReceiverGroup?: IMsgReceiverGroupMp) {}
}
