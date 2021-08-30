import { Gender } from '../models/gender';

export interface Person{
  id: number;
  name: string;
  email: string;
  cpf: string;
  birthDate: string;
  startDate: string;
  team: string;
  gender: Gender;
}