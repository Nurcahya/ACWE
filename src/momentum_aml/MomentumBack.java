package momentum_aml;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MomentumBack implements Cloneable
{
	protected double fLearningRate;
	protected Layer[] fLayers;
	protected TransferFunction fTransferFunction;
        public double fMomentum;
        public double wow;
        public double wew;

	public MomentumBack(int[] layers, double learningRate, TransferFunction fun, double momentum)
	{
		fLearningRate = learningRate;
		fTransferFunction = fun;
		fMomentum = momentum;
		fLayers = new Layer[layers.length];
		
		for(int i = 0; i < layers.length; i++)
		{			
			if(i != 0)
			{
				fLayers[i] = new Layer(layers[i], layers[i - 1]);
			}
			else
			{
				fLayers[i] = new Layer(layers[i], 0);
			}
		}
	}
	

	public double[] execute(double[] input)
	{
		int i;
		int j;
		int k;
		double new_value;
		
		double output[] = new double[fLayers[fLayers.length - 1].Length];
		
		// beri input
		for(i = 0; i < fLayers[0].Length; i++)
		{
			fLayers[0].Neurons[i].Value = input[i];
		}
		
		// Execute - hiddens + output
		for(k = 1; k < fLayers.length; k++)
		{
			for(i = 0; i < fLayers[k].Length; i++)
			{
				new_value = 0.0;
				for(j = 0; j < fLayers[k - 1].Length; j++)
					new_value += fLayers[k].Neurons[i].Weights[j] * fLayers[k - 1].Neurons[j].Value+fMomentum*new_value;
				
				new_value += fLayers[k].Neurons[i].Bias+fMomentum*new_value;
				
				fLayers[k].Neurons[i].Value = fTransferFunction.evalute(new_value);
			}
		}
		
		
		// dapatkan output
		for(i = 0; i < fLayers[fLayers.length - 1].Length; i++)
		{
			output[i] = fLayers[fLayers.length - 1].Neurons[i].Value;
		}
		
		return output;
	}
	

	public double backPropagateMultiThread(double[] input, double[] output, int nthread)
	{
		return 0.0;
	}


	public double backPropagate(double[] input, double[] output, int epoch)
	{
		double new_output[] = execute(input);
		double error;
		int i;
		int j;
		int k;
		
		for(i = 0; i < fLayers[fLayers.length - 1].Length; i++)
		{
			error = output[i] - new_output[i];
			fLayers[fLayers.length - 1].Neurons[i].Delta = error * fTransferFunction.evaluteDerivate(new_output[i]);
		} 
	
		
		for(k = fLayers.length - 2; k >= 0; k--)
		{
			// menghitung error untuk mendapatkan delta
			for(i = 0; i < fLayers[k].Length; i++)
			{
				error = 0.0;
				for(j = 0; j < fLayers[k + 1].Length; j++)
					error += fLayers[k + 1].Neurons[j].Delta * fLayers[k + 1].Neurons[j].Weights[i];
								
				fLayers[k].Neurons[i].Delta = error * fTransferFunction.evaluteDerivate(fLayers[k].Neurons[i].Value);				
			}
			// update bobot dari lapisan berikutnya
			for(i = 0; i < fLayers[k + 1].Length; i++)
			{
                            
                            if (epoch==0){
                           // System.out.println(epoch);
                           for(j = 0; j < fLayers[k].Length; j++)
					fLayers[k + 1].Neurons[i].Weights[j] += fLearningRate * fLayers[k + 1].Neurons[i].Delta * 
							fLayers[k].Neurons[j].Value;
                                        fLayers[k + 1].Neurons[i].Bias += fLearningRate * fLayers[k + 1].Neurons[i].Delta;
                            
                            }
                            
                            else {
                               
				for(j = 0; j < fLayers[k].Length; j++){
            			fLayers[k + 1].Neurons[i].Weights[j] = fLearningRate * fLayers[k + 1].Neurons[i].Delta * 
							fLayers[k].Neurons[j].Value +fLayers[k+1].Neurons[i].Weights[j];
                               // double ea1 = fLearningRate * fLayers[k + 1].Neurons[i].Delta * fLayers[k].Neurons[j].Value;
                                //double ea2 = (fMomentum * fLayers[k+1].Neurons[i].Weights[j]);
                                //double ea3 = fLayers[k+1].Neurons[i].Weights[j];
                                //System.out.println(ea1+" - "+ea2+" - "+ea3);
                                
                               }
                                fLayers[k + 1].Neurons[i].Bias = fLearningRate * fLayers[k + 1].Neurons[i].Delta  + fLayers[k+1].Neurons[i].Bias ;
                                
			}
                        }
		}	
		
		// Hitung error 
		error = 0.0;
		
		for(i = 0; i < output.length; i++)
		{
			error += Math.abs(new_output[i] - output[i]);
			//error += Math.pow(Math.abs(new_output[i] - output[i]),2);
			//System.out.println(output[i]+" "+new_output[i]);
		}

		error = error / output.length;
		return error;
	}
	
	
	public boolean save(String path)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
		}
		catch (Exception e) 
		{ 
			return false;
		}
		
		return true;
	}
	
	
//	public static MomentumBack load(String path)
//	{
//		try
//		{
//			MomentumBack net;	
//			FileInputStream fin = new FileInputStream(path);
//			ObjectInputStream oos = new ObjectInputStream(fin);
//			net = (MomentumBack) oos.readObject();
//			oos.close();
//			
//			return net;
//		}
//		catch (Exception e) 
//		{ 
//			return null;
//		}
//	}
//	
	

	/**
	 * @return nilai pembelajaran konstan
	 */
	public double getLearningRate()
	{
		return fLearningRate;
	}
	
	/**
	 * 
	 * @param rate
	 */
	public void	setLearningRate(double rate)
	{
		fLearningRate = rate;
	}
	
	/**
	 * Mengatur fungsi transfer baru
        *
        * Param Fun Fungsi Transfer
	 */
	public void setTransferFunction(TransferFunction fun)
	{
		fTransferFunction = fun;
	}	
	
	/**
	 * @return Dimensi layer di input
	 */
	public int getInputLayerSize()
	{
		return fLayers[0].Length;
	}
	
	/**
	 * @return Dimensi layer di output
	 */
	public int getOutputLayerSize()
	{
		return fLayers[fLayers.length - 1].Length;
	}
}

