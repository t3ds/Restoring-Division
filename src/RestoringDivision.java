//All the algorithm computation takes place here

public class RestoringDivision {

    StringBuilder buffer = new StringBuilder();
    int[] A;
    int[] Q;
    int[] M;
    int flag = 0,ptr = 0;

    private int findIndex(int[] X)
    {
        int i;
        for(i=0;i<8;i++)
        {
            if(M[i] == 1)
            {
                break;
            }
        }
        return i;
    }

    public void convertArray(int[] X)
    {
        for (int i = 0; i < 8; i++) {
            if(X[i]==-1)
                buffer.append('_');
            else
                buffer.append(Integer.toString(X[i]));
        }
    }
    public int[] binary(int x, int[] arr)
    {
        int i=1;
        do{
            arr[8-i] = x%2;
            x = x/2;
            i++;
        }while(x!=0);

        return arr;
    }
//The compute() method is the gateway method between the two classes and handles passing to other functions as well as returning the final value
    public String compute(int[] C, int[] D)
    {
        A = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        M=D;
        Q=C;
        ptr = findIndex(M);
        System.out.println(ptr);
        for (int i =0; i<8; i++)
        {
            flag++;
            leftShift();
            /*buffer.append("\t\t");
            convertArray(Q);*/
            buffer.append("\n\n");

        }

        return buffer.toString();

    }

    public void leftShift()
    {
        buffer.append("Left Shift\t\t\t");
        int temp;

        temp=Q[0];
        for(int i =1; i<=7; i++)
        {
            Q[i-1] = Q[i];
            A[i-1] = A[i];
        }

        Q[7] = -1;
        A[7] = temp;

        convertArray(M);
        buffer.append("\t\t");

        convertArray(A);
        buffer.append("\t\t");

        convertArray(Q);


        subtract();
    }

    public void subtract()
    {
        int[] temporary = A.clone();


        int x,b;
        b=0;

        buffer.append("\n\nA=A-M  \t\t\t\t\t\t");
        convertArray(A);
        buffer.append("\n-\t\t\t\t\t\t\t");
        convertArray(M);
        buffer.append("\n\t\t\t\t\t\t\t-----------\n\t\t\t\t\t\t\t");

        for(int i = 7; i>=0; i--)
        {

            x = A[i];
            A[i] = b^x^M[i];
            //FULL SUBTRACTOR LOGIC FOR SUBTRACTION
            if((((x) == 0) && (b == 1)) || (((x) == 0) && (M[i] == 1)) || ((M[i] == 1) && (b == 1))) {
                b = 1;
            }

            else {
                b = 0;
            }

        }


        convertArray(A);


        if(A[ptr-1] == 1) {
            Q[7] = 0;

        }


        else {
            Q[7] = 1;
        }

        buffer.append("\t\t");
        convertArray(Q);

        if(A[ptr-1] == 1) {
            A = temporary;
            buffer.append("\n\nRestoring:\t\t\t\t\t");
            convertArray(A);
        }
    }

}
